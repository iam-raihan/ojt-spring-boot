package com.bjit.raihan.services;

import com.bjit.raihan.entity.OrderEntity;
import com.bjit.raihan.repository.IExtendedRepository;
import com.bjit.raihan.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements IService<OrderEntity, IExtendedRepository<OrderEntity>> {

    private final OrderRepository orderRepository;

    @Override
    public OrderRepository getRepository() {
        return orderRepository;
    }
}
