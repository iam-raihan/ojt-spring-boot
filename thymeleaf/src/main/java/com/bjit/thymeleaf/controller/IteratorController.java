package com.bjit.thymeleaf.controller;

import com.bjit.thymeleaf.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
