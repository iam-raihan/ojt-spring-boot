package com.bjit.persistence.controller;

import com.bjit.persistence.entity.Order;
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
    	
    	orders.add(Order.builder().name("ORDER 1").street("Street 1").build());
    	orders.add(Order.builder().name("ORDER 2").street("Street 2").build());
    	orders.add(Order.builder().name("ORDER 3").street("Street 3").build());
    	orders.add(Order.builder().name("ORDER 4").street("Street 4").build());
    	
    	model.addAttribute("orders",orders);

        return "iterate";
    }
   
    
}
