package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GenerateTSDef
public class ReqProxyAsset {
    private String origin;
    private String clientInfo;
    private Optional<Boolean> body = Optional.empty();
}
