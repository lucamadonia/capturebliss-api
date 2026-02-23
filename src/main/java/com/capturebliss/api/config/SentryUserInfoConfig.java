package com.capturebliss.api.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.capturebliss.api.service.UserService;
import io.sentry.protocol.User;
import io.sentry.spring.jakarta.SentryUserProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SentryUserInfoConfig implements SentryUserProvider {
  private final UserService userService;

  public SentryUserInfoConfig(UserService userService) {
    this.userService = userService;
  }

  public User provideUser() {
    User sentryUser = new User();
    try {
      var auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
        // Only set email from custom claim if available, don't trigger fallback chain
        var claims = jwt.getClaims();
        Object userDetailsClaim = claims.get("https://identity.capturebliss.com/user");
        if (userDetailsClaim != null) {
          String claimStr = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(userDetailsClaim);
          UserService.UserClaimFromAuth0 captureblissUser = new com.fasterxml.jackson.databind.ObjectMapper()
            .readValue(claimStr, UserService.UserClaimFromAuth0.class);
          if (captureblissUser != null && captureblissUser.email() != null) {
            sentryUser.setEmail(captureblissUser.email());
          }
        }
        return sentryUser;
      }
    } catch (Exception e) {
      log.warn("Could not set Sentry user info: {}", e.getMessage());
    }
    return null;
  }
}
