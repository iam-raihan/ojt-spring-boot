package com.bjit.raihan.services;

import com.bjit.raihan.entity.OrderEntity;
import com.bjit.raihan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IService<OrderEntity, OrderRepository> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderRepository getRepository() {
        return orderRepository;
    }
}
