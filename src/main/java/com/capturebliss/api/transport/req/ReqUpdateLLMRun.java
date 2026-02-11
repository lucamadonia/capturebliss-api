package com.capturebliss.api.transport.req;

import com.capturebliss.api.common.LLMOpsStatus;
import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OptionalPropInTS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GenerateTSDef
public class ReqUpdateLLMRun {
  private Long id;
  @OptionalPropInTS
  private LLMOpsStatus status;
  @OptionalPropInTS
  private Object data;
  @OptionalPropInTS
  private Object meta;
}
