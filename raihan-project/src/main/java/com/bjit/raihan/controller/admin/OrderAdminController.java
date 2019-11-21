package com.bjit.raihan.controller.admin;

import com.bjit.raihan.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/orders", name = "admin.orders")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderService orderService;

    // TODO CRUD operation
}
