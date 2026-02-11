package com.capturebliss.api.repo;

import com.capturebliss.api.common.EntityConfigConfigType;
import com.capturebliss.api.common.TopLevelEntityType;
import com.capturebliss.api.common.TourWithConfig;
import com.capturebliss.api.entity.DemoEntity;
import com.capturebliss.api.transport.TourDeleted;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DemoEntityRepo extends CrudRepository<DemoEntity, Long> {
  List<DemoEntity> findAllByBelongsToOrgAndDeletedAndEntityTypeEqualsOrderByUpdatedAtDesc(Long belongsToOrgId, TourDeleted deleted, TopLevelEntityType type);

  List<DemoEntity> findAllByBelongsToOrgAndDeleted(Long belongsToOrgId, TourDeleted deleted);

  List<DemoEntity> findAllByBelongsToOrgAndDeletedAndLastPublishedDateNotNull(Long orgId, TourDeleted deleted);

  Optional<DemoEntity> findByRid(String rid);

  Optional<DemoEntity> findByRidAndEntityType(String rid, TopLevelEntityType type);

  Optional<DemoEntity> findByRidAndDeletedEquals(String rid, TourDeleted deleted);

  Optional<DemoEntity> findByRidAndDeletedAndEntityType(String rid, TourDeleted deleted, TopLevelEntityType entityType);

  Optional<DemoEntity> findByRidAndDeleted(String rid, TourDeleted deleted);

  @Query("SELECT new com.capturebliss.api.common.TourWithConfig(t, e) " +
    "FROM DemoEntity t " +
    "LEFT OUTER JOIN EntityConfigKV e ON t.belongsToOrg = e.entityId AND e.configType IN (:entityConfigConfigTypes) " +
    "WHERE t.rid = :rid AND t.deleted = :deleted AND t.entityType = :entityType ORDER BY t.id")
  List<TourWithConfig> findTourWithConfigByRidAndDeletedAndEntityType(String rid, TourDeleted deleted, Set<EntityConfigConfigType> entityConfigConfigTypes, TopLevelEntityType entityType);

  @Query(value = "SELECT new com.capturebliss.api.common.TourWithConfig(t, e) " +
    "FROM DemoEntity t " +
    "LEFT OUTER JOIN EntityConfigKV e ON t.belongsToOrg = e.entityId AND e.configType IN (:entityConfigConfigTypes) " +
    "WHERE t.id = :id ORDER BY t.id")
  List<TourWithConfig> findTourWithConfigById(Long id, Set<EntityConfigConfigType> entityConfigConfigTypes);

  List<DemoEntity> findAllByIdIn(List<Long> id);

  List<DemoEntity> findAllByRidInAndDeletedEquals(List<String> rids, TourDeleted deleted);
}
