package com.capturebliss.analytics.entity;

import com.capturebliss.analytics.transport.RespHouseLead;
import com.capturebliss.api.common.TransportObjRef;
import com.capturebliss.api.entity.EntityBase;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;

@Entity
@Table(name = "d_house_lead", schema = "al")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder(toBuilder = true)
@TransportObjRef(cls = RespHouseLead.class)
public class MHouseLead extends EntityBase {
  @Column(name = "enc_entity_id")
  private Long entityId;

  private String pkVal;
  private String pkField;
  private String aid;
  private Integer sessionCreated;
  private Integer timeSpentSec;
  private Timestamp lastInteractedAt;
  private Integer completionPercentage;
  private Integer ctaClickRate;

  @Type(JsonType.class)
  @Column(columnDefinition = "json")
  private Object info;
}
