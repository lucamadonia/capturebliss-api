package com.capturebliss.api.transport;

import com.capturebliss.api.entity.PlatformIntegration;
import com.capturebliss.api.entity.TenantIntegration;
import com.capturebliss.api.transport.resp.RespOrg;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@GenerateTSDef
public class RespFatTenantIntegration {
  private RespOrg org;
  private PlatformIntegration platformIntegration;
  private TenantIntegration tenantIntegration;
}
