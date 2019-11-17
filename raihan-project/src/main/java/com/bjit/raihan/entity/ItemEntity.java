package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String name;

    @Min(value = 0)
    private double price;

    private Types type = Types.SubItem;

    public enum Types {
        MainItem, SubItem
    }

    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    private void setUpdatedAt() {
        this.updatedAt = new Date();
    }

    @PreRemove
    private void beforeRemove() {
        // remove from pivot table
    }
}
