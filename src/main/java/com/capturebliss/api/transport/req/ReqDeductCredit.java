package com.capturebliss.api.transport.req;

import com.capturebliss.api.common.SubscriptionCreditType;
import com.capturebliss.api.transport.GenerateTSDef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@GenerateTSDef
@NoArgsConstructor
@AllArgsConstructor
public class ReqDeductCredit {
  Integer deductBy;
  SubscriptionCreditType creditType;
}
