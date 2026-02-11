package com.capturebliss.analytics.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.capturebliss.api.common.TopLevelEntityType;
import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OptionalPropInTS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@GenerateTSDef
public class InActivityLog {
  @JsonProperty("evtt")
  private Timestamp eventTime;

  @JsonProperty("eni")
  private Long entityId;

  @JsonProperty("ent")
  private TopLevelEntityType entityType;

  @JsonProperty("enc")
  private LogForEntityCategory entityCategory;

  private String event;

  @OptionalPropInTS
  private String target;

  private String aid;

  private String sid;

  private Integer offset;

  private String tz;

  private Object payload;
}
