package com.capturebliss.api.transport.req;

import com.capturebliss.api.common.Utils;
import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public record ReqDuplicateTour(String duplicateTourName, String fromTourRid) {

    public ReqDuplicateTour normalizeDisplayName() {
        return new ReqDuplicateTour(Utils.normalizeWhitespace(duplicateTourName), fromTourRid);
    }
}
