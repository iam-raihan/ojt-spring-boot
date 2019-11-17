package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank()
    private String details;

    @Min(value = 0)
    private double totalPrice;

    private Date orderDate;

    @PrePersist
    private void orderDate() {
        this.orderDate = new Date();
    }

    public OrderEntity setItems(List<ItemEntity> items) {
        details = items.stream()
                .map(ItemEntity::getName)
                .collect(Collectors.joining(", "));

        totalPrice = items.stream()
                .mapToDouble(ItemEntity::getPrice)
                .sum();

        return this;
    }
}
