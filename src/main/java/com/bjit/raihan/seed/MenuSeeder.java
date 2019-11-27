package com.bjit.raihan.seed;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.repository.ItemRepository;
import com.bjit.raihan.repository.MenuRepository;
import com.bjit.raihan.utils.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuSeeder implements ISeeder<MenuEntity, MenuRepository> {

    private final MenuRepository menuRepository;
    private final ItemRepository itemRepository;

    @Override
    public MenuRepository repository() {
        return menuRepository;
    }

    @Override
    public List<MenuEntity> getEntities() {
        List<ItemEntity> items = itemRepository.findAll();

        MenuEntity menu1 = MenuEntity.builder()
                .name("Set Menu 1")
                .items(ListHelper.subList(items, 0, 5, 6, 7))
                .build();
        MenuEntity menu2 = MenuEntity.builder()
                .name("Set Menu 2")
                .items(ListHelper.subList(items, 1, 5, 6, 7))
                .build();
        MenuEntity menu3 = MenuEntity.builder()
                .name("Set Menu 3")
                .items(ListHelper.subList(items, 2, 5, 6, 7))
                .build();

        return List.of(menu1, menu2, menu3);
    }
}
