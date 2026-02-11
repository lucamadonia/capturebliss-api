package com.capturebliss.api.transport;

import com.capturebliss.api.common.ForObjectType;
import com.capturebliss.api.common.LogType;

import java.util.Optional;

@GenerateTSDef
public record ReqNewLog(
  Long orgId,
  LogType logType,
  ForObjectType forObjectType,
  Long forObjectId,
  Optional<String> forObjectKey,
  Object logLine
) {
}
