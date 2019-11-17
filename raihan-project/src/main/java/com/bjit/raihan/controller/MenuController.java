package com.bjit.raihan.controller;

import com.bjit.raihan.repository.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @GetMapping
    public ResponseEntity getMenu() {
        return ResponseEntity.ok(menuDao.findAll());
    }

    @GetMapping("/delete")
    public ResponseEntity remove() {
        var x = menuDao.findById(3);
        x.ifPresent(menuEntity -> menuDao.delete(menuEntity));

        return ResponseEntity.ok(menuDao.findAll());
    }
}
