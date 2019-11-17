package com.bjit.raihan.repository;

import com.bjit.raihan.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {
}
