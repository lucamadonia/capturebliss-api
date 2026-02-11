package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.NfEvents;

import java.util.Map;

@GenerateTSDef
public record ReqNfHook(NfEvents eventName, Map<String, String> payload) {
}
