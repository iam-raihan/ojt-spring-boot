package com.bjit.persistence.controller;

import com.bjit.persistence.error.MailConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/property")
public class CustomPropertyController {

	@Autowired
	MailConfiguration mailConfiguration;

	@GetMapping()
	public String orderForm(Model model) {

		model.addAttribute("host", mailConfiguration.getHostName());
		model.addAttribute("port", mailConfiguration.getPort());
		model.addAttribute("from", mailConfiguration.getFrom());
		return "property";
	}

}
