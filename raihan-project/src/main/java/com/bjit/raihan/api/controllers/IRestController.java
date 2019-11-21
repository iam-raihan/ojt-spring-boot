package com.bjit.raihan.api.controllers;

import com.bjit.raihan.api.ApiResponse;
import com.bjit.raihan.entity.BaseEntity;
import com.bjit.raihan.repository.IExtendedRepository;
import com.bjit.raihan.services.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public interface IRestController<TEntity extends BaseEntity
        , TService extends IService<TEntity, IExtendedRepository<TEntity>>> {

    TService getService();

    @GetMapping
    default ResponseEntity getAll() {
        List<TEntity> data = getService().findAll();
        return new ApiResponse().send(data);
    }

    @GetMapping("/{id}")
    default ResponseEntity getById(@PathVariable Long id) {
        TEntity data = getService().findById(id);
        return new ApiResponse("Entity Found").send(data);
    }

    @PostMapping
    default ResponseEntity create(@Valid @RequestBody TEntity entity) {
        TEntity data = getService().save(entity);
        return new ApiResponse("Entity Created").created(data);
        //  return ResponseEntity.created(...URI...).build();
    }

    @PutMapping("/{id}")
    default ResponseEntity update(@Valid @RequestBody TEntity entity, @PathVariable Long id) {
        TEntity data = getService().update(entity, id);
        return new ApiResponse("Entity Updated").accepted(data);
    }

    @DeleteMapping("/{id}")
    default ResponseEntity delete(@PathVariable Long id) {
        getService().deleteById(id);
        return new ApiResponse("Entity Deleted").send();
    }
}
