package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.EntityType;
import com.capturebliss.api.transport.GenerateTSDef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@GenerateTSDef
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqEntityAssetAssn {
    private String entityRid;

    private EntityType entityType;
}
