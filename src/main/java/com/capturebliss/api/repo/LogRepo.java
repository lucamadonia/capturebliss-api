package com.capturebliss.api.repo;

import com.capturebliss.api.common.ForObjectType;
import com.capturebliss.api.common.LogType;
import com.capturebliss.api.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogRepo extends CrudRepository<Log, Long> {
  Optional<Log> getFirstLogByOrgIdAndLogTypeAndForObjectTypeAndForObjectKeyOrderByUpdatedAtDesc(
    Long orgId,
    LogType logType,
    ForObjectType forObjectType,
    String forObjectKey
  );
}
