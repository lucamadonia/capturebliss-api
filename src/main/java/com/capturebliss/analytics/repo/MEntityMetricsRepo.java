package com.capturebliss.analytics.repo;

import com.capturebliss.analytics.entity.MEntityMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MEntityMetricsRepo extends JpaRepository<MEntityMetrics, Long> {

}
