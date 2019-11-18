package com.bjit.raihan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EqualsAndHashCode()
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // or
    // @SequenceGenerator(name = "mySeqGen", sequenceName = "menuSeq", allocationSize = 10)
    // @GeneratedValue(generator = "mySeqGen")
    protected Long id;

    protected Date createdAt;

    protected Date updatedAt;

    /**
     * Soft delete
     */
    @JsonIgnore
    protected Boolean isDeleted = false;

    @PrePersist
    protected void setCreatedAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void setUpdatedAt() {
        this.updatedAt = new Date();
    }
}
