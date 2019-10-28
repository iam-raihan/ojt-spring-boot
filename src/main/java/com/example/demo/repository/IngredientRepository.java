package com.example.demo.repository;

import com.example.demo.entity.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();
	 Ingredient findOne(String id);
	 Ingredient save(Ingredient ingredient);

}
