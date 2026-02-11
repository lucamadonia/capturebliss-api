package com.capturebliss.api.repo;

import com.capturebliss.api.entity.Job;
import com.capturebliss.api.transport.JobType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepo extends CrudRepository<Job, Long> {
    Optional<Job> findFirstByJobTypeAndJobKey(JobType jobType, String jobKey);
}
