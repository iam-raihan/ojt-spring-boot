package com.bjit.raihan.controller;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.entity.OrderEntity;
import com.bjit.raihan.services.ItemService;
import com.bjit.raihan.services.MenuService;
import com.bjit.raihan.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/orders", name = "orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MenuService menuService;
    private final ItemService itemService;

    @GetMapping(
            value = "",
            name = "view"
    )
    public String viewOrders(Model model) {
        // TODO - get all by auth user id
        List<OrderEntity> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "orders/index";
    }

    @GetMapping(
            value = "/create",
            name = "create.view"
    )
    public String viewNewOrderForm(Model model) {
        List<MenuEntity> menus = menuService.findAll();
        List<ItemEntity> items = itemService.findAll();

        model.addAttribute("menus", menus);
        model.addAttribute("items", items);

        return "orders/create";
    }

    @GetMapping(
            value = "/create/menu/{id}",
            name = "create.menu.view"
    )
    public String viewNewOrderForm(Model model, @PathVariable Long id) {
        List<MenuEntity> menus = menuService.findAll();
        List<ItemEntity> items = itemService.findAll();
        MenuEntity selectedMenu = menus.stream()
                .filter(menu -> menu.getId().equals(id))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);

        model.addAttribute("menus", menus);
        model.addAttribute("selectedMenu", selectedMenu);
        model.addAttribute("items", items);

        return "orders/create";
    }

    @PostMapping(
            value = "",
            name = "create"
    )
    public String create(@Valid @RequestBody OrderEntity order, Errors errors) {
        if (errors.hasErrors()) {
            // TODO
            return "";
        }

        orderService.save(order);
        // TODO - show notification
        return "redirect:/orders";
    }
}
