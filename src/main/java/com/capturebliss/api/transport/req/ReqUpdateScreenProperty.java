package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public record ReqUpdateScreenProperty(String rid, String propName, Object propValue) {
}
