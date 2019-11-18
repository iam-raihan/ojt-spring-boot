package com.bjit.raihan.api.controllers;

import com.bjit.raihan.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/items")
public class ItemRestController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Long id) {
        return ResponseEntity.ok(itemRepository.findById(id));
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable Long id) {
        var x = itemRepository.findById(id);
        x.ifPresent(itemEntity -> {
            entityManager.createNativeQuery("DELETE FROM MENU_ITEMS WHERE ITEM_ID = ?")
                    .setParameter(1, itemEntity.getId())
                    .executeUpdate();

            itemRepository.delete(itemEntity);
        });

        return ResponseEntity.ok(itemRepository.findAll());
    }
}
