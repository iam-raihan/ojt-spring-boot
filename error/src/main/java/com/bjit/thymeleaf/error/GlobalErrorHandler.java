package com.bjit.thymeleaf.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {
	@ExceptionHandler(RecordNotFoundException.class)
	public String handleCustomException(RecordNotFoundException ex, Model model) {
		model.addAttribute("errorMessage",ex.getMessage());
		return "error/recordNotFound";
	}
}
