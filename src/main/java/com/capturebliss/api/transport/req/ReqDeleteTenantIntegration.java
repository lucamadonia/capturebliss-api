package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@GenerateTSDef
public class ReqDeleteTenantIntegration {
  Long tenantIntegrationId;
}
