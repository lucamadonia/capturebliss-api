package com.capturebliss.api.transport.req;

import com.capturebliss.api.common.PlatformIntegrationType;

import java.util.Map;

public record ReqAddOrUpdatePlatformIntegrationConfig(
  PlatformIntegrationType type,
  String name,
  String icon,
  String description,
  Boolean disabled,
  Map<String, Object> config
) {
}
