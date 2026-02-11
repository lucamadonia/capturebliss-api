package com.capturebliss.analytics.repo;

import com.capturebliss.analytics.entity.ActivityDt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDtRepo extends JpaRepository<ActivityDt, Long> {

}
