package com.bjit.raihan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity(name = "Orders")
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @NotBlank
    private String details;

    @Min(value = 0)
    private double totalPrice;

    @NotNull(message = "Order date is required")
    @Column(nullable = false)
    private Date orderDate;

    public OrderEntity(List<ItemEntity> items) {
        details = items.stream()
                .map(ItemEntity::getName)
                .collect(Collectors.joining(", "));

        totalPrice = items.stream()
                .mapToDouble(ItemEntity::getPrice)
                .sum();
    }

    public OrderEntity(MenuEntity menu) {
        this(menu.getItems());
    }

    @PrePersist
    private void orderDate() {
        if (orderDate == null)
            orderDate = new Date();
    }

    public String readableOrderDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        return sdf.format(orderDate);
    }
}
