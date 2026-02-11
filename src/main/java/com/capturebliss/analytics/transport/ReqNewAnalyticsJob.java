package com.capturebliss.analytics.transport;

import com.capturebliss.analytics.common.AnalyticsJobType;
import com.capturebliss.analytics.common.ProcessingStatus;
import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OptionalPropInTS;
import lombok.Data;

import java.sql.Timestamp;

@Data
@GenerateTSDef
public class ReqNewAnalyticsJob {
  private AnalyticsJobType jobType;

  private String jobKey;

  private ProcessingStatus jobStatus;

  @OptionalPropInTS
  private Timestamp lowWatermark;

  @OptionalPropInTS
  private Timestamp highWatermark;

  @OptionalPropInTS
  private Object jobData;
}
