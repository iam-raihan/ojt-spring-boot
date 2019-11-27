package com.bjit.raihan.controller.admin;

import com.bjit.raihan.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/menus", name = "admin.menus")
@RequiredArgsConstructor
public class MenuAdminController {

    private final MenuService menuService;

    // TODO CRUD operation
}
