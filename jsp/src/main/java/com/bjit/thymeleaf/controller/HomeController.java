package com.bjit.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("/home")
	public String orderForm(Model model) {
		model.addAttribute("welcome", "Welcome to BJIT");
		return "home";
	}

	

}
