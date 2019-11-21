package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity(name = "Menus")
@Table(name = "menus")
public class MenuEntity extends BaseEntity {

    @NotBlank()
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MENU_ITEMS"
            , joinColumns = @JoinColumn(name = "MENU_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_MENU_ID"))
            , inverseJoinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ITEM_ID"))
            , uniqueConstraints = @UniqueConstraint(name = "UK_MENU_ID", columnNames = {"MENU_ID", "ITEM_ID"})
        )
    private List<ItemEntity> items;

    public Double getTotalPrice() {
        if (items == null)
            return 0.0;

        return items.stream().mapToDouble(ItemEntity::getPrice).sum();
    }

    // if getMenuDetails() then auto includes in json
    public String menuDetails() {
        if (items == null)
            return "";

        String details = items.stream()
                .map(ItemEntity::getName)
                .collect(Collectors.joining(", "));

        return "Package includes: " + details;
    }
}
