package com.capturebliss.api.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@GenerateTSDef
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WhiteLabelConfig {
  @OptionalPropInTS
  private String appName;          // Custom app name (replaces "Capturebliss")
  @OptionalPropInTS
  private String logoUrl;          // Custom logo URL
  @OptionalPropInTS
  private String faviconUrl;       // Custom favicon URL
  @OptionalPropInTS
  private String primaryColor;     // Primary brand color (hex, e.g. "#7567ff")
  @OptionalPropInTS
  private String secondaryColor;   // Secondary brand color
  @OptionalPropInTS
  private String accentColor;      // Accent color
  @OptionalPropInTS
  private String fontFamily;       // Custom font family
  @OptionalPropInTS
  private String customDomain;     // Custom domain for white-label
  @OptionalPropInTS
  private Boolean hidePoweredBy;   // Hide "Powered by Capturebliss" branding
  @OptionalPropInTS
  private String customCss;        // Custom CSS overrides
  @OptionalPropInTS
  private String loginBackground;  // Custom login page background
  @OptionalPropInTS
  private String supportEmail;     // Custom support email
  @OptionalPropInTS
  private String supportUrl;       // Custom support URL
}
