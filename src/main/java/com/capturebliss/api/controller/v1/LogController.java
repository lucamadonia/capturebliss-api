package com.capturebliss.api.controller.v1;

import com.capturebliss.api.common.ApiResp;
import com.capturebliss.Routes;
import com.capturebliss.api.service.LogService;
import com.capturebliss.api.transport.ReqNewLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.API_V1)
@Slf4j
@RequiredArgsConstructor
public class LogController {
  private final LogService logService;

  @RequestMapping(value = Routes.NEW_LOG, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResp<String> appendLog(@RequestBody ReqNewLog req) {
    logService.appendNewLogLine(req);
    return ApiResp.<String>builder().status(ApiResp.ResponseStatus.Success).data("ok").build();
  }

}
