package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Order;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", Order.builder().build());
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order,Errors errors) {
		if(errors.hasErrors()) {
    		return "orderForm";
    	}
	 log.info("Order submitted: " + order);
	 return "redirect:/";
	}
	

}