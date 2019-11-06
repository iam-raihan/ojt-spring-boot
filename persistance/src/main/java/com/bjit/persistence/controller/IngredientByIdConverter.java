package com.bjit.persistence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bjit.persistence.entity.Ingredient;
import com.bjit.persistence.repository.IngredientRepository;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  
  @Override
  public Ingredient convert(String id) {
    return ingredientRepo.findById(id);
  }

}
