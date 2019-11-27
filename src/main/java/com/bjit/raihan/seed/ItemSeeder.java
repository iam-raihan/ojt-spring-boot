package com.bjit.raihan.seed;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.entity.ItemEntity.Types;
import com.bjit.raihan.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ItemSeeder implements ISeeder<ItemEntity, ItemRepository> {
    private final ItemRepository itemRepository;
    private List<ItemEntity> items;

    @Override
    public ItemRepository repository() {
        return itemRepository;
    }

    public List<ItemEntity> getEntities() {
        items = new ArrayList<>();

        getMainItems().forEach(
                (name, price) -> items.add(getEntity(name, price, Types.MainItems))
        );
        getSubItems().forEach(
                (name, price) -> items.add(getEntity(name, price, Types.SubItems))
        );
        getSnacksItems().forEach(
                (name, price) -> items.add(getEntity(name, price, Types.Snacks))
        );

        return items;
    }

    private ItemEntity getEntity(String name, Double price, ItemEntity.Types type) {
        return ItemEntity.builder()
                .name(name)
                .price(price)
                .type(type)
                .build();
    }

    private Map<String, Double> getMainItems() {
        Map<String, Double> items = new LinkedHashMap<>();
        items.put("Chicken Curry", 60d);    // 0
        items.put("Beef Bhuna", 80d);       // 1
        items.put("Mutton", 100d);          // 2
        items.put("Fish", 60d);             // 3

        return items;
    }

    private Map<String, Double> getSubItems() {
        Map<String, Double> items = new LinkedHashMap<>();
        items.put("Mashed Potato", 20d);    // 4
        items.put("Rice", 20d);             // 5
        items.put("Dal", 0d);               // 6
        items.put("Salad", 0d);             // 7

        return items;
    }

    private Map<String, Double> getSnacksItems() {
        Map<String, Double> items = new LinkedHashMap<>();
        items.put("Coke", 20d);
        items.put("Pepsi", 20d);
        items.put("Water (250 ml)", 15d);
        items.put("Mr. Twist Chips", 15d);
        items.put("Vegetable Roll", 20d);
        items.put("Egg Chop", 20d);

        return items;
    }
}
