package com.bjit.raihan.controller;

import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

	private final MenuService menuService;

	@GetMapping(
			value = "",
			name = "home"
	)
	public String viewIndex(Model model) {
		List<MenuEntity> menus = menuService.findAll()
				.stream()
				.limit(3)
				.collect(Collectors.toList());
		model.addAttribute("menus", menus);

		return "index";
	}
}
