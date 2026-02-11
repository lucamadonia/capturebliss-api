package com.capturebliss.api.transport.req;

import com.capturebliss.api.common.EntityInfo;
import com.capturebliss.api.common.Utils;
import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.TourSettings;

import java.util.Optional;

@GenerateTSDef
public record ReqNewTour(String name, Optional<String> description, Optional<TourSettings> settings,
                         Optional<EntityInfo> info) {
  public ReqNewTour normalizeDisplayName() {
    return new ReqNewTour(Utils.normalizeWhitespace(name()), description(), settings(), info());
  }
}
