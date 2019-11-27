package com.bjit.raihan.controller.admin;

import com.bjit.raihan.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/items", name = "admin.items")
@RequiredArgsConstructor
public class ItemAdminController {

    private final ItemService itemService;

    // TODO CRUD operation
}
