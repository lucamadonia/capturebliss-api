package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;

import java.util.Map;

@GenerateTSDef
public record ReqCobaltEvent(String event, Map<String, Object> payload) {
}
