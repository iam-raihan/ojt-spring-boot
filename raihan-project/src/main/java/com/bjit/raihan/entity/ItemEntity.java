package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity(name = "Items")
@Table(name = "items")
public class ItemEntity extends BaseEntity {

    @NotBlank
    private String name;

    @Min(value = 0)
    private double price;

    @Builder.Default
    private Types type = Types.SubItem;

    public enum Types {
        MainItem, SubItem
    }

    @PreRemove
    private void beforeRemove() {
        // remove from pivot table
    }
}
