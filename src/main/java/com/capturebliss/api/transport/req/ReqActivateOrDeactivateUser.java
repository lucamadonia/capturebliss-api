package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public record ReqActivateOrDeactivateUser(
    Long userId,
    Boolean shouldActivate
) {
}
