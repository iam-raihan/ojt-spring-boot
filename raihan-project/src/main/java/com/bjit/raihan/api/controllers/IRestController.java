package com.bjit.raihan.api.controllers;

import com.bjit.raihan.entity.BaseEntity;
import com.bjit.raihan.services.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public interface IRestController<TEntity extends BaseEntity
        , TService extends IService> {

    TService getService();

    @GetMapping
    default ResponseEntity getAll() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    default ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    default ResponseEntity create(@Valid @RequestBody TEntity entity) {
        getService().save(entity);
        //  return ResponseEntity.created(...URI...).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(getService().save(entity));
    }

    @PutMapping("/{id}")
    default ResponseEntity update(@Valid @RequestBody TEntity entity, @PathVariable Long id) {
        entity.setId(id);
        return ResponseEntity.ok(getService().save(entity));
    }

    @DeleteMapping("/{id}")
    default ResponseEntity delete(@PathVariable Long id) {
        getService().deleteById(id);
        return ResponseEntity.ok().build();
    }
}
