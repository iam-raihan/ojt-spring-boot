package com.bjit.raihan.repository;

import com.bjit.raihan.entity.OrderEntity;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends IExtendedRepository<OrderEntity> {

}
