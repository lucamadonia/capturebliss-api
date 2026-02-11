package com.capturebliss.api.transport.vendor;

import java.util.List;

public record ReqZapierWebhookReg(
  String hookUrl,
  List<Long> tourIds,
  String event
) {
}
