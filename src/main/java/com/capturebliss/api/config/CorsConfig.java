package com.capturebliss.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
  private final CleanupInterceptor cleanupInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // allow cors for all path for localhost and staging at this point in time
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
      .allowedHeaders("Authorization", "Content-Type", "X-Id-Token", "Accept")
      .allowCredentials(false)
      .maxAge(3600);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(cleanupInterceptor);
  }
}
