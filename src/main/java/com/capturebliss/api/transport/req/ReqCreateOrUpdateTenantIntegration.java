package com.capturebliss.api.transport.req;

import com.capturebliss.api.common.PlatformIntegrationType;
import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OptionalPropInTS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@GenerateTSDef
public class ReqCreateOrUpdateTenantIntegration {
  PlatformIntegrationType integrationType;
  @OptionalPropInTS
  Long tenantIntegrationId;
  @OptionalPropInTS
  Long relayId;
  String event;
  @OptionalPropInTS
  Boolean disabled;
  @OptionalPropInTS
  Long tourId;
  Map<String, Object> tenantConfig;
}
