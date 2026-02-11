package com.capturebliss.api.common;

import com.fasterxml.jackson.annotation.JsonValue;
import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public enum TopLevelEntityType {
  TOUR(0),
  DEMO_HUB(1);

  public final Integer value;

  TopLevelEntityType(Integer value) {
    this.value = value;
  }


  @JsonValue
  public int getValue() {
    return value;
  }
}
