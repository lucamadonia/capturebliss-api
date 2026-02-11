package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public record ReqLeadActivityDataPost(Long tourId, String aid, String data) {
}
