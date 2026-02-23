package com.capturebliss.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.capturebliss.api.common.UnauthorizedReason;
import com.capturebliss.api.common.Utils;
import com.capturebliss.api.config.OrgContext;
import com.capturebliss.api.entity.Org;
import com.capturebliss.api.entity.User;
import com.capturebliss.api.repo.UserRepo;
import com.capturebliss.api.transport.NfEvents;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Qualifier("userService")
public class UserService {
  private final UserRepo userRepo;
  private final NfHookService nfHookService;
  private final SubscriptionService subService;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuerUri;

  ObjectMapper objectMapper = new ObjectMapper();

  public UserService(UserRepo userRepo, NfHookService nfHookService, SubscriptionService subService) {
    this.userRepo = userRepo;
    this.nfHookService = nfHookService;
    this.subService = subService;
  }

  // used from AuthUser annotation
  public User getOrCreateUserFromJwt(Jwt jwt) throws JsonProcessingException {
    String subject = jwt.getSubject();

    // Fast path: find existing user by auth ID (works even when custom JWT claim is missing)
    Optional<User> existingUser = userRepo.findByAuthId(subject);
    if (existingUser.isPresent()) {
      User user = existingUser.get();
      Long orgId = OrgContext.getCurrentOrgId();
      User updatedUser = setLatestOrgForUser(user, orgId);
      return setUserActiveOrInactive(updatedUser, true);
    }

    // New user: need claims for creation (email, name, picture)
    UserClaimFromAuth0 userClaimFromAuth0 = getUserClaimsFromAuth0(jwt);
    User user = userRepo.findUserByEmail(userClaimFromAuth0.email())
      .orElseGet(() -> createNewUser(userClaimFromAuth0, subject));

    if (!StringUtils.equalsIgnoreCase(user.getAuthId(), subject)) {
      log.error("{} is trying to login using subject {} but subject already exists {}",
        user.getEmail(), user.getAuthId(), subject);
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, objectMapper.writeValueAsString(
        Map.of("r", UnauthorizedReason.EmailIdExistsButLoginMethodDoesNotMatch)
      ));
    }

    Long orgId = OrgContext.getCurrentOrgId();
    User updatedUser = setLatestOrgForUser(user, orgId);
    return setUserActiveOrInactive(updatedUser, true);
  }

  public UserClaimFromAuth0 getUserClaimsFromAuth0(Jwt jwt) throws JsonProcessingException {
    Map<String, Object> claims = jwt.getClaims();
    Object userDetailsClaim = claims.get("https://identity.capturebliss.com/user");
    if (userDetailsClaim == null) {
      log.warn("Custom claim missing from JWT, falling back to userinfo endpoint");
      return getUserClaimsFromUserInfoEndpoint(jwt);
    }
    String userDetailsClaimStr = objectMapper.writeValueAsString(userDetailsClaim);
    return objectMapper.readValue(userDetailsClaimStr, UserClaimFromAuth0.class);
  }

  private UserClaimFromAuth0 getUserClaimsFromUserInfoEndpoint(Jwt jwt) {
    Map<String, Object> claims = jwt.getClaims();

    // First: try standard JWT claims (some Auth0 configs include email in access token)
    String email = claims.get("email") instanceof String e ? e : null;
    if (email != null && email.contains("@")) {
      String picture = claims.get("picture") instanceof String p ? p : null;
      String givenName = claims.get("given_name") instanceof String g ? g : null;
      String familyName = claims.get("family_name") instanceof String f ? f : null;
      log.info("Got user info from standard JWT claims: email={}", email);
      return new UserClaimFromAuth0(picture, email, familyName, givenName);
    }

    // Second: try /userinfo endpoint
    try {
      String userinfoUrl = issuerUri + (issuerUri.endsWith("/") ? "" : "/") + "userinfo";
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(userinfoUrl))
        .header("Authorization", "Bearer " + jwt.getTokenValue())
        .GET()
        .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        Map<String, Object> userInfo = objectMapper.readValue(response.body(), Map.class);
        email = (String) userInfo.get("email");
        String picture = (String) userInfo.get("picture");
        String givenName = (String) userInfo.get("given_name");
        String familyName = (String) userInfo.get("family_name");
        log.info("Got user info from userinfo endpoint: email={}", email);
        return new UserClaimFromAuth0(picture, email, familyName, givenName);
      } else {
        log.warn("Userinfo endpoint returned status={} (access tokens for custom APIs cannot call /userinfo)", response.statusCode());
      }
    } catch (Exception e) {
      log.error("Error calling userinfo endpoint", e);
    }

    // Third: check if sub looks like an email (e.g. passwordless or some social providers)
    String sub = jwt.getSubject();
    if (sub != null && sub.contains("@")) {
      log.info("Using JWT subject as email: {}", sub);
      return new UserClaimFromAuth0(null, sub, null, null);
    }

    // Cannot determine user email - throw clear error instead of using auth0|xxx as email
    log.error("Cannot determine user email. Custom claim missing, /userinfo failed, sub={}. " +
      "Ensure Auth0 Post-Login Action adds the custom claim to access tokens.", sub);
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
      "Cannot determine user identity. Please logout and login again.");
  }

  User setUserActiveOrInactive(User user, Boolean isActive) {
    if (isActive == user.getActive()) return user;
    user.setActive(isActive);
    User changedUser = userRepo.save(user);
    subService.updateNoOfSeatInSubscription(user.getBelongsToOrg());
    return changedUser;
  }

  public User createNewUser(UserClaimFromAuth0 user, String authId) {
    Pair<String, Boolean> domainInf = Utils.getDomainFromEmailForRespectiveEmail(user.email());
    String emailDomain = domainInf.getValue0();
    Boolean isWorkEmail = domainInf.getValue1();

    if (StringUtils.isBlank(emailDomain)) {
      log.error("Can't find domain from email {}", user.email());
      throw new IllegalStateException("Can't create user");
    }

    String firstName = StringUtils.substring(user.givenName, 0, 49);
    String lastName = StringUtils.substring(user.familyName, 0, 49);
    if (StringUtils.isBlank(firstName)) {
      firstName = StringUtils.substringBefore(user.email(), "@");
    }

    User newUser = User.builder()
      .email(user.email())
      .avatar(user.picture())
      .authId(authId)
      .firstName(firstName)
      .lastName(StringUtils.defaultIfBlank(lastName, ""))
      .domainBlacklisted(!isWorkEmail)
      .active(true)
      .build();
    sendUserNf(user.email(), newUser.getFirstName(), newUser.getLastName());
    return userRepo.save(newUser);
  }

  public void sendUserNf(String userEmail, String firstName, String lastName) {
    Map<String, String> eventInfo = new HashMap<>();

    if (StringUtils.isBlank(userEmail) || StringUtils.isBlank(firstName)) {
      log.warn("Didn't send message as one of userEmail=[{}] or firstName=[{}] is blank", userEmail, firstName);
      return;
    }

    eventInfo.put("emailId", userEmail);
    eventInfo.put("firstName", firstName);
    if (!StringUtils.isBlank(lastName)) eventInfo.put("lastName", lastName);
    nfHookService.sendNotification(NfEvents.NEW_USER_SIGNUP, eventInfo);
  }

  public void sendUserNf(String userEmail, String params) {
    Map<String, String> eventInfo = new HashMap<>();

    if (StringUtils.isBlank(userEmail)) {
      log.warn("Didn't send message as one of userEmail=[{}] or details=[{}]", userEmail, params);
      return;
    }

    eventInfo.put("emailId", userEmail);
    eventInfo.put("params", params);
    nfHookService.sendNotification(NfEvents.NEW_USER_SIGNUP, eventInfo);
  }


  public User setLatestOrgForUser(User user, Long orgId) throws JsonProcessingException {
    if (orgId != null) {
      Set<Org> orgs = user.getOrgs();
      orgs = orgs == null ? Set.of() : orgs;
      boolean isOrgValid = false;
      for (Org org : orgs) {
        if (Objects.equals(org.getId(), orgId)) {
          user.setBelongsToOrg(orgId);
          isOrgValid = true;
          break;
        }
      }
      if (!isOrgValid) {
        log.error("orgId {} is passed but user {} is not associated with org", orgId, user.getEmail());
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, objectMapper.writeValueAsString(
          Map.of("r", UnauthorizedReason.OrgSuggestedButInvalidAssociation)
        ));
      }
    }
    return user;
  }

  public record UserClaimFromAuth0(String picture, String email, String familyName, String givenName) {
  }
}
