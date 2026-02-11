package com.capturebliss.api.entity;

import com.capturebliss.api.transport.JobProcessingInfo;
import com.capturebliss.api.transport.JobProcessingStatus;
import com.capturebliss.api.transport.JobType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Job extends EntityBase {
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;

    @Column(nullable = false)
    private String jobKey;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private JobProcessingStatus processingStatus;

    private String failureReason;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private JobProcessingInfo info;
}
