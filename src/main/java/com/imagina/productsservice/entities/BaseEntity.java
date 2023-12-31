package com.imagina.productsservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name= "created_by", nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name= "modified_by", nullable = false)
    private String modifiedBy;

    @CreatedDate
    @Column(name= "created_date", nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name= "modified_date", nullable = false)
    private LocalDateTime modifiedDate;
}
