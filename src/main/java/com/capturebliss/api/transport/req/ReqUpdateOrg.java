package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OrgInfo;

@GenerateTSDef
public record ReqUpdateOrg(OrgInfo orgInfo) {
}
