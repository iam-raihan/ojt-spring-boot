package com.bjit.persistence.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/layout")
public class LayoutController {
	
	 @GetMapping
	    public String showDesignForm(Model model) {
	    	
	        return "layout/index";
	    }
}
