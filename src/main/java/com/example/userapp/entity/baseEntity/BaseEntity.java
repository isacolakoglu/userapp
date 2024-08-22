package com.example.userapp.entity.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@MappedSuperclass
public class BaseEntity implements Serializable {

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private LocalDate createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDate updatedAt;

}
