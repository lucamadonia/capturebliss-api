package com.capturebliss.api.transport.req;


import com.capturebliss.api.transport.GenerateTSDef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GenerateTSDef
public class ReqMediaProcessing {
  private String path;
  private String cdnPath;
  private ReqEntityAssetAssn assn;
}
