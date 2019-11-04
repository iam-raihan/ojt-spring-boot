package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.Ingredient.Type;
import com.example.demo.entity.Order;
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
@RequestMapping("/iterate")
public class IteratorController {
	
    @GetMapping
    public String getOrders(Model model) {
    	List<Order> orders = new ArrayList<>();
    	
    	orders.add(Order.builder().deliveryName("ORDER 1").id(101l).build());
    	orders.add(Order.builder().deliveryName("ORDER 2").id(102l).build());
    	orders.add(Order.builder().deliveryName("ORDER 3").id(103l).build());
    	orders.add(Order.builder().deliveryName("ORDER 4").id(104l).build());
    	
    	model.addAttribute("orders",orders);

        return "iterate";
    }
   
    
}
