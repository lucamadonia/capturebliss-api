package com.capturebliss.api.transport.resp;

import com.capturebliss.api.transport.GenerateTSDef;
import lombok.Data;

@GenerateTSDef
@Data
public class RespSubsValidation {
  private boolean isCardPresent;
}
