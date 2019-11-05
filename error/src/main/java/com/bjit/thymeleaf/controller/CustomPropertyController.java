package com.bjit.thymeleaf.controller;

import com.bjit.thymeleaf.entity.Order;
import com.bjit.thymeleaf.error.InvalidParseException;
import com.bjit.thymeleaf.error.MailConfiguration;
import com.bjit.thymeleaf.error.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
