package com.bjit.raihan.services;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService implements IService<ItemEntity, ItemRepository> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ItemRepository getRepository() {
        return itemRepository;
    }
}
