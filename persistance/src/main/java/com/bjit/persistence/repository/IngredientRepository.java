package com.bjit.persistence.repository;

import com.bjit.persistence.entity.Ingredient;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();

	Ingredient findById(String id);

	Ingredient save(Ingredient ingredient);

}
