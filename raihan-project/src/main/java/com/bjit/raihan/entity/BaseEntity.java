package com.bjit.raihan.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // or
    // @SequenceGenerator(name = "mySeqGen", sequenceName = "menuSeq", allocationSize = 10)
    // @GeneratedValue(generator = "mySeqGen")
    protected Long id;

    protected Date createdAt;

    protected Date updatedAt;

    @PrePersist
    protected void setCreatedAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void setUpdatedAt() {
        this.updatedAt = new Date();
    }
}
