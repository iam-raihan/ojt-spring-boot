package com.bjit.spring.security.data;

import com.bjit.spring.security.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
