package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private Integer Id;

    @NotBlank(message = "Menu name is required")
    private String name;
}
