package com.bjit.spring.security.data;

import com.bjit.spring.security.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
