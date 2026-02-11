package com.capturebliss.analytics.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.capturebliss.analytics.entity.AidRichInfo;
import com.capturebliss.analytics.entity.MHouseLead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HouseLeadWithRichInfo {
  private MHouseLead lead;
  private AidRichInfo info;
}
