package com.bjit.raihan.api.controllers;

import com.bjit.raihan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
}
