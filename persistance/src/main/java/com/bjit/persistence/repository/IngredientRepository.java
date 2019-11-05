package com.bjit.persistence.repository;


import com.bjit.persistence.entity.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();
	 Ingredient findOne(String id);
	 Ingredient save(Ingredient ingredient);

}
