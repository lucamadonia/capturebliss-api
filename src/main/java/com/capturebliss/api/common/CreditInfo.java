package com.capturebliss.api.common;

import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OptionalPropInTS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenerateTSDef
public class CreditInfo {
  private int value;
  @OptionalPropInTS
  private int absValue = 0;
  private Date updatedAt;
}
