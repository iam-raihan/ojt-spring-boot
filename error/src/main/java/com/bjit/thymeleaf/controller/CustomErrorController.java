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


@Controller
@Slf4j
@RequestMapping("/cerror")
public class CustomErrorController {

    @GetMapping("/{id}")
    public String orderForm(Model model, @PathVariable("id") Integer id) {
        if (id > 1) {
            throw new RecordNotFoundException("Record Not Found Exception");
        }
        model.addAttribute("order", Order.builder().build());
        return "orderForm";
    }

    @GetMapping("invalid/{id}")
    public String invalid(Model model, @PathVariable("id") Integer id) {
        if (id > 1) {
            throw new InvalidParseException("Invalid Data Parse");
        }
        model.addAttribute("order", Order.builder().build());
        return "orderForm";
    }


    @ExceptionHandler(InvalidParseException.class)
    public String invalidParseException(Model model, Exception ex) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/invalidParse";
    }


}
