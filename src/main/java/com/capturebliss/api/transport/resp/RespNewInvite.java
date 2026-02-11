package com.capturebliss.api.transport.resp;

import com.capturebliss.api.transport.GenerateTSDef;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@GenerateTSDef
public class RespNewInvite {
  private String code;

  public static RespNewInvite Empty() {
    return new RespNewInvite();
  }
}
