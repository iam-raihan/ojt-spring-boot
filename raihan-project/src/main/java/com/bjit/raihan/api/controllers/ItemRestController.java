package com.bjit.raihan.api.controllers;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.services.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@Slf4j
@RequiredArgsConstructor
public class ItemRestController implements IRestController<ItemEntity, ItemService> {

    private final ItemService itemService;

    @Override
    public ItemService getService() {
        return itemService;
    }
}
