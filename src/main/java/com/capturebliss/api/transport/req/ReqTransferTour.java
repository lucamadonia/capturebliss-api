package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;

import java.util.List;

@GenerateTSDef
public record ReqTransferTour(String email, Long orgId, List<String> rids) {
}
