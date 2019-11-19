package com.bjit.raihan.api.controllers;

import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.services.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@Slf4j
@RequiredArgsConstructor
public class MenuRestController implements IRestController<MenuEntity, MenuService> {

    private final MenuService menuService;

    @Override
    public MenuService getService() {
        return menuService;
    }
}
