package com.bjit.raihan.services;

import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MenuService implements IService<MenuEntity, MenuRepository>{

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuRepository getRepository() {
        return menuRepository;
    }

    public List<MenuEntity> findAll() {
        return IService.super.findAll();
    }

    @Cacheable(cacheNames = "menus", key = "#id")
    public MenuEntity findById(Long id) throws EntityNotFoundException {
        return IService.super.findById(id);
    }

    @CachePut(cacheNames = "menus", key = "#entity.id")
    public MenuEntity save(MenuEntity entity) {
        return IService.super.save(entity);
    }

    @CacheEvict(cacheNames = "menus", key = "#id")
    public void deleteById(Long id) {
        IService.super.deleteById(id);
    }

    @CacheEvict(value="menus", allEntries = true)
    public void clearCache() { }
}
