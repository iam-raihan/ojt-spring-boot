package com.bjit.persistence.jpa.data;

import com.bjit.persistence.jpa.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
