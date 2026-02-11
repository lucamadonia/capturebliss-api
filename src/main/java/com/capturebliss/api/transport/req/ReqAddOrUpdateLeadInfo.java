package com.capturebliss.api.transport.req;

import com.capturebliss.api.common.LeadInfoKey;
import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public record ReqAddOrUpdateLeadInfo(
  Long tourId,
  String emailId,
  String value,
  LeadInfoKey key
) {
}
