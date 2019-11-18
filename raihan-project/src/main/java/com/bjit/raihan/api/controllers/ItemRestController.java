package com.bjit.raihan.api.controllers;

import com.bjit.raihan.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
