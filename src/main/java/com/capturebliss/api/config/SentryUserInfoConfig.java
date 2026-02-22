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
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Jwt) {
      Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      try {
        UserService.UserClaimFromAuth0 captureblissUser = userService.getUserClaimsFromAuth0(jwt);
        if (captureblissUser != null && captureblissUser.email() != null) {
          sentryUser.setEmail(captureblissUser.email());
        }
        return sentryUser;
      } catch (JsonProcessingException e) {
        log.error("Error while sending user to sentry");
        e.printStackTrace();
      }
    }
    return null;
  }
}
