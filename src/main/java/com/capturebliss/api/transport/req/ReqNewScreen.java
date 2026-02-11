package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.ScreenType;

import java.util.Optional;

import static com.capturebliss.api.common.Utils.normalizeWhitespace;

@GenerateTSDef
public record ReqNewScreen(
    String name,
    Optional<String> url,
    Optional<String> thumbnail, // base64 image data
    Optional<String> favIcon,
    ScreenType type,
    Optional<String> contentType,
    String body
) {
    public Long normalizedParentId() {
        return 0L;
    }

    public ReqNewScreen normalizeDisplayName() {
        return new ReqNewScreen(normalizeWhitespace(name()), url(), thumbnail(), favIcon(), type(), contentType(), body());
    }
}
