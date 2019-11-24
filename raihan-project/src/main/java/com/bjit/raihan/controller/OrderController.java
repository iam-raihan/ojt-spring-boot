package com.bjit.raihan.controller;

import com.bjit.raihan.dto.OrderDTO;
import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.entity.OrderEntity;
import com.bjit.raihan.services.ItemService;
import com.bjit.raihan.services.MenuService;
import com.bjit.raihan.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
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
        Collections.reverse(orders);
        model.addAttribute("orders", orders);

        return "orders/index";
    }

    @GetMapping(
            value = "/create",
            name = "create.view"
    )
    public String viewNewOrderForm(Model model) {
        List<ItemEntity> items = itemService.findAll();
        OrderDTO orderDTO = new OrderDTO().setItems(items);
        model.addAttribute("orderDTO", orderDTO);

        return "orders/create";
    }

    @GetMapping(
            value = "/create/menu/{id}",
            name = "create.menu.view"
    )
    public String viewNewOrderForm(Model model, @PathVariable Long id) {
        MenuEntity menu = menuService.findById(id);
        // TODO - handle EntityNotFoundException
        List<ItemEntity> items = itemService.findAll();
        OrderDTO orderDTO = new OrderDTO().setItems(items);
        orderDTO.setSelectedItems(menu.getItems());

        model.addAttribute("orderDTO", orderDTO);

        return "orders/create";
    }

    @PostMapping(
            value = "/create",
            name = "create"
    )
    public String create(@Valid OrderDTO orderDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ItemEntity> items = itemService.findAll();
            model.addAttribute("orderDTO", orderDTO.setItems(items));

            return "orders/create";
        }

        orderService.save(orderDTO.toOrder());
        // TODO - show notification
        return "redirect:/orders";
    }
}
