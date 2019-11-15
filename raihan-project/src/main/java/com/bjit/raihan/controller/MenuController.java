package com.bjit.raihan.controller;

import com.bjit.raihan.data.MenuDao;
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
        return ResponseEntity.ok(menuDao.findById(1));
    }
}
