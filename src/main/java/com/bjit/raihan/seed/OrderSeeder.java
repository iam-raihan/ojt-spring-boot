package com.bjit.raihan.seed;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.entity.OrderEntity;
import com.bjit.raihan.repository.ItemRepository;
import com.bjit.raihan.repository.MenuRepository;
import com.bjit.raihan.repository.OrderRepository;
import com.bjit.raihan.utils.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderSeeder implements ISeeder<OrderEntity, OrderRepository> {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MenuRepository menuRepository;

    @Override
    public OrderRepository repository() {
        return orderRepository;
    }

    @Override
    public List<OrderEntity> getEntities() {
        List<ItemEntity> items = itemRepository.findAll();
        List<MenuEntity> menus = menuRepository.findAll();

        OrderEntity order1 = new OrderEntity(menus.get(1));
        OrderEntity order2 = new OrderEntity(menus.get(2));
        OrderEntity order3 = new OrderEntity(ListHelper.subList(items, 4, 5, 7, 9));

        return List.of(order1, order2, order3);
    }
}
