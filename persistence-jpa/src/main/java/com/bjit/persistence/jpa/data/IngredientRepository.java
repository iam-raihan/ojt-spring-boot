package com.bjit.persistence.jpa.data;

import com.bjit.persistence.jpa.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
