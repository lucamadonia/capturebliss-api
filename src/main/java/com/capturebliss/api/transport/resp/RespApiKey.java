package com.capturebliss.api.transport.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.capturebliss.api.common.Utils;
import com.capturebliss.api.entity.ApiKey;
import com.capturebliss.api.transport.GenerateTSDef;
import io.sentry.Sentry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Slf4j
@GenerateTSDef
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespApiKey extends ResponseBase {
  private String apiKey;
  private Boolean active;
  private RespUser createdBy;

  public static RespApiKey from(ApiKey apiKey) {
    try {
      return (RespApiKey) Utils.fromEntityToTransportObject(apiKey);
    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
             InvocationTargetException e) {
      log.error("Can't convert entity to transport object. Error: " + e.getMessage());
      Sentry.captureException(e);
      return Empty();
    }
  }


  private static RespApiKey Empty() {
    return new RespApiKey();
  }
}
