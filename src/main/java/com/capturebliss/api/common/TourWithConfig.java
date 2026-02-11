package com.capturebliss.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.capturebliss.api.entity.DemoEntity;
import com.capturebliss.api.entity.EntityConfigKV;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourWithConfig {
  private DemoEntity demoEntity;
  private EntityConfigKV entityConfigKV;
}
