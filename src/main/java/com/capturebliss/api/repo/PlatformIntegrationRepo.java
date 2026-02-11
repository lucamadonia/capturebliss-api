package com.capturebliss.api.repo;

import com.capturebliss.api.entity.PlatformIntegration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformIntegrationRepo extends CrudRepository<PlatformIntegration, Long> {
}
