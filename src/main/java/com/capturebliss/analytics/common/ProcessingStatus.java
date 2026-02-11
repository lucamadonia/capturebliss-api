package com.capturebliss.analytics.common;

import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public enum ProcessingStatus {
  Waiting,
  InProgress,
  Successful,
  Failed
}
