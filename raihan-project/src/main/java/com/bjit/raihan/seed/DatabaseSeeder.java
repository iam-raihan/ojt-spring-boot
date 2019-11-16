package com.bjit.raihan.seed;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.entity.OrderEntity;
import com.bjit.raihan.repository.ItemRepository;
import com.bjit.raihan.repository.MenuRepository;
import com.bjit.raihan.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        /*
         *  Seed Items
         */
        ItemEntity item1 = ItemEntity.builder()
                .name("Chicken")
                .price(80)
                .type(ItemEntity.Types.MainItem)
                .build();

        ItemEntity item2 = ItemEntity.builder()
                .name("Beef")
                .price(100)
                .type(ItemEntity.Types.MainItem)
                .build();

        ItemEntity item3 = ItemEntity.builder()
                .name("Dal")
                .price(0)
                .type(ItemEntity.Types.SubItem)
                .build();

        itemRepository.deleteAll();
        itemRepository.saveAll(Arrays.asList(item1, item2, item3));

        /*
         *  Seed Menu
         */
        MenuEntity menu1 = MenuEntity.builder().name("Set Menu 1").items(List.of(item1, item3)).build();
        MenuEntity menu2 = MenuEntity.builder().name("Set Menu 2").items(List.of(item2, item3)).build();
        MenuEntity menu3 = MenuEntity.builder().name("Set Menu 3").items(List.of(item1, item2)).build();

        menuRepository.deleteAll();
        menuRepository.saveAll(Arrays.asList(menu1, menu2, menu3));

        /*
         *  Seed Order
         */
        OrderEntity order1 = new OrderEntity().setItems(List.of(item1, item2));
        OrderEntity order2 = new OrderEntity().setItems(List.of(item2, item3));
        OrderEntity order3 = new OrderEntity().setItems(List.of(item1, item3));

        orderRepository.deleteAll();
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
    }
}
