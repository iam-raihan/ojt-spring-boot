package com.bjit.raihan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // or
    // @SequenceGenerator(name = "mySeqGen", sequenceName = "menuSeq", allocationSize = 10)
    // @GeneratedValue(generator = "mySeqGen")
    protected Long id;

//    @CreationTimestamp
    @Column(nullable = false)
    protected Date createdAt;

//    @UpdateTimestamp
    protected Date updatedAt;

    /**
     * TODO - Implement Soft delete in Repository
     */
    @JsonIgnore
    @Column(nullable = false)
    protected Boolean isDeleted = false;

    @PrePersist
    protected void setCreatedAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void setUpdatedAt() {
        this.updatedAt = new Date();
    }

    @PreRemove
    private void beforeRemove() { }
}
