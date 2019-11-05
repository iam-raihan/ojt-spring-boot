package com.bjit.persistence.repository;

import com.bjit.persistence.entity.Order;

public interface OrderRepository {
	Order save(Order order);
}
