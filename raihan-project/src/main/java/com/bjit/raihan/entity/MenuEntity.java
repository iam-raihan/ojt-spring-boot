package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "menus")
public class MenuEntity {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "menuSeq", allocationSize = 10)
    @GeneratedValue(generator = "mySeqGen")
    // or
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
