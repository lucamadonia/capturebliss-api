package com.capturebliss.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.capturebliss.api.entity.EntityConfigKV;
import com.capturebliss.api.entity.Subscription;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionWithCredit {
  private Subscription subscription;
  private EntityConfigKV entityConfigKV;
}
