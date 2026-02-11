package com.capturebliss.analytics.repo;

import com.capturebliss.analytics.common.AnalyticsJobType;
import com.capturebliss.analytics.common.ProcessingStatus;
import com.capturebliss.analytics.entity.AnalyticsJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalyticsJobRepo extends JpaRepository<AnalyticsJob, Long> {
  Optional<AnalyticsJob> findFirstByJobTypeAndJobStatusOrderByUpdatedAtDesc(AnalyticsJobType jobType, ProcessingStatus status);
}
