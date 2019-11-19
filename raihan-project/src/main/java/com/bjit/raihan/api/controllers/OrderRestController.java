package com.bjit.raihan.api.controllers;

import com.bjit.raihan.entity.OrderEntity;
import com.bjit.raihan.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderRestController implements IRestController<OrderEntity, OrderService> {

    private final OrderService orderService;

    @Override
    public OrderService getService() {
        return orderService;
    }
}
