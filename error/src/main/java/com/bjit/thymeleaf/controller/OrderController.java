package com.bjit.thymeleaf.controller;

import com.bjit.thymeleaf.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {
	
	//end::baseClass[]
	//tag::orderForm[]
	  @GetMapping("/current")
	  public String orderForm(Model model) {
	    model.addAttribute("order", Order.builder().build());
	    return "orderForm";
	  }
	//end::orderForm[]

	/*
	//tag::handlePost[]
	  @PostMapping
	  public String processOrder(Order order) {
	    log.info("Order submitted: " + order);
	    return "redirect:/";
	  }
	//end::handlePost[]
	*/
	  
	//tag::handlePostWithValidation[]
	  @PostMapping
	  public String processOrder(@Valid Order order, Errors errors) {
	    if (errors.hasErrors()) {
	      return "orderForm";
	    }
	    
	    log.info("Order submitted: " + order);
	    return "redirect:/";
	  }
	//end::handlePostWithValidation[]
	  
	//tag::baseClass[]
	

}
