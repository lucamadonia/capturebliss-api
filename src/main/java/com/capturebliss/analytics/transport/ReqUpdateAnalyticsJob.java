package com.capturebliss.analytics.transport;

import com.capturebliss.analytics.common.ProcessingStatus;
import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OptionalPropInTS;
import lombok.Data;

import java.sql.Timestamp;

@Data
@GenerateTSDef
public class ReqUpdateAnalyticsJob {
  @OptionalPropInTS
  private ProcessingStatus jobStatus;

  @OptionalPropInTS
  private Timestamp lowWatermark;

  @OptionalPropInTS
  private Timestamp highWatermark;

  @OptionalPropInTS
  private String failureReason;

  @OptionalPropInTS
  private Object jobData;
}
