package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.Ingredient.Type;
import com.example.demo.entity.Taco;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.JdbcIngredientRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
private IngredientRepository ingredientRepository;
	
	
	@Autowired
	public DesignTacoController(JdbcIngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

    @GetMapping
    public String showDesignForm(Model model) {
    	List<Ingredient> ingredients = new ArrayList<>();
    	ingredientRepository.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }
    
    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design,Errors errors, Model model) {
    	if(errors.hasErrors()) {
    		return "design";
    	}
     // Save the taco design...
     // We'll do this in chapter 3
     log.info("Processing design: " + design);
     return "redirect:/orders/current";
    }

    //tag::filterByType[]
    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

//end::filterByType[]

   
    
   
}
