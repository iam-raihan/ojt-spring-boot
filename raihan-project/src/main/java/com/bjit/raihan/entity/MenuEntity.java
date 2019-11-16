package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity(name = "Menus")
@Table(name = "menus")
public class MenuEntity extends BaseEntity {

    @NotBlank(message = "Menu name is required")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MENU_ITEMS"
            , joinColumns = @JoinColumn(name = "MENU_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_MENU_ID"))
            , inverseJoinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ITEM_ID"))
            , uniqueConstraints = @UniqueConstraint(name = "UK_MENU_ID", columnNames = {"MENU_ID", "ITEM_ID"})
        )
    private List<ItemEntity> items;
}
