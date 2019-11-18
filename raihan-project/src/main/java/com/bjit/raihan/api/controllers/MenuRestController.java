package com.bjit.raihan.api.controllers;

import com.bjit.raihan.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuRestController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity find() {
        return ResponseEntity.ok(menuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.findById(id));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        menuService.deleteById(id);
        return ResponseEntity.ok(new Object());
    }
}
