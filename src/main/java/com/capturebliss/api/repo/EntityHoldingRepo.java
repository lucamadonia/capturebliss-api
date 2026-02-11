package com.capturebliss.api.repo;

import com.capturebliss.api.entity.EntityHolding;
import org.springframework.data.repository.CrudRepository;

public interface EntityHoldingRepo extends CrudRepository<EntityHolding, Long> {
}
