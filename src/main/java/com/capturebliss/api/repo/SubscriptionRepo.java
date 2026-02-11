package com.capturebliss.api.repo;

import com.capturebliss.api.common.ConfigEntityType;
import com.capturebliss.api.common.EntityConfigConfigType;
import com.capturebliss.api.common.SubscriptionWithCredit;
import com.capturebliss.api.entity.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepo extends CrudRepository<Subscription, Long> {
  Subscription getSubscriptionByOrgId(Long orgId);

  Subscription getSubscriptionByCbSubscriptionId(String id);

  Subscription getSubscriptionByCbCustomerId(String id);

  @Query(value = "SELECT new com.capturebliss.api.common.SubscriptionWithCredit(t, e) " +
    "FROM Subscription t " +
    "LEFT OUTER JOIN EntityConfigKV e ON t.orgId = e.entityId AND e.configType = :type AND e.entityType = :configType " +
    "WHERE t.orgId = :id ORDER BY t.id")
  List<SubscriptionWithCredit> getSubscriptionAndCredit(Long id, ConfigEntityType configType, EntityConfigConfigType type);
}
