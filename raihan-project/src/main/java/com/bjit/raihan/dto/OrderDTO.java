package com.bjit.raihan.dto;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.entity.OrderEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Slf4j
public class OrderDTO {

    private Map<ItemEntity.Types, List<ItemEntity>> groupedItems;

    @NotNull(message = "* Select at least one item")
    private List<ItemEntity> selectedItems;

    @NotBlank(message = "Enter delivery location")
    private String location;

    @NotNull(message = "Enter date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date orderDate;

    public OrderDTO setItems(List<ItemEntity> allItems) {
        groupedItems = allItems.stream().collect(
                Collectors.groupingBy(ItemEntity::getType
                        , LinkedHashMap::new // to maintain item.type order
                        , Collectors.toList()
                )
        );

        return this;
    }

    public boolean isSelected(Long id) {
        if (selectedItems == null)
            return false;
        return selectedItems.stream().anyMatch(item -> item.getId().equals(id));
    }

    public OrderEntity toOrder() {
        OrderEntity entity = new OrderEntity(selectedItems);
        entity.setOrderDate(orderDate);
        // TODO - set location
        return entity;
    }
}
