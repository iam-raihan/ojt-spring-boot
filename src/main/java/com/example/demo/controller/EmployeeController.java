package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
	
	@GetMapping(value = "/test/{id}")
    public RedirectView handleTestRequest (RedirectAttributes attributes,Model model) {
		attributes.addAttribute("attr", "attrVal");
		attributes.addAttribute("testPath", "pathValue");
		attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/employee/test2/{testPath}/{id}");
        return rv;
    }

	@GetMapping("/test2/{testPath}/{id}")
    public String handleRequest (@PathVariable("testPath") String testPath,
    								@PathVariable("id") String id,
                                 @RequestParam("attr") String attr,
                                 @ModelAttribute("flashAttribute") String flashAttribute,
                                 Model model) {

        model.addAttribute("id", id);
        model.addAttribute("attr", attr);
        return "delete";
    }
	

}
