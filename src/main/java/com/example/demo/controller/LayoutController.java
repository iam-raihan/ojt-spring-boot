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
@RequestMapping("/layout")
public class LayoutController {
	
	 @GetMapping
	    public String showDesignForm(Model model) {
	    	
	        return "layout/index";
	    }
}
