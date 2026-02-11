package com.capturebliss.api.service;

import com.capturebliss.api.common.ForObjectType;
import com.capturebliss.api.common.LogType;
import com.capturebliss.api.entity.Log;
import com.capturebliss.api.repo.LogRepo;
import com.capturebliss.api.transport.ReqNewLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogService {
  private final LogRepo logRepo;

  @Transactional
  public void appendNewLogLine(ReqNewLog req) {
    Log line = Log.builder()
      .orgId(req.orgId())
      .logType(req.logType())
      .forObjectType(req.forObjectType())
      .forObjectId(req.forObjectId())
      .logLine(req.logLine())
      .forObjectKey(req.forObjectKey().orElse(null))
      .build();

    logRepo.save(line);
  }

  @Transactional
  public Optional<Log> getLicenseFromLog(String license) {
    return logRepo.getFirstLogByOrgIdAndLogTypeAndForObjectTypeAndForObjectKeyOrderByUpdatedAtDesc(
      0L,
      LogType.SUBSCRIPTION,
      ForObjectType.LIFETIME_LICENSE_KEY,
      license
    );
  }
}
