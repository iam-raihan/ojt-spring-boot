package com.bjit.raihan.services;

import com.bjit.raihan.entity.MenuEntity;
import com.bjit.raihan.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements IService<MenuEntity, MenuRepository>{

    private final MenuRepository menuRepository;

    @Override
    public MenuRepository getRepository() {
        return menuRepository;
    }

    @Cacheable(cacheNames = "menus")
    public List<MenuEntity> findAll() {
        return IService.super.findAll();
    }

    @Cacheable(cacheNames = "menu", key = "#id")
    public MenuEntity findById(Long id) throws EntityNotFoundException {
        return IService.super.findById(id);
    }

    @CachePut(cacheNames = "menu", key = "#entity.id")
    public MenuEntity save(MenuEntity entity) {
        return IService.super.save(entity);
    }

    @CachePut(cacheNames = "menu", key = "#entity.id")
    public MenuEntity update(MenuEntity entity, Long id) {
        clearCache();
        return IService.super.update(entity, id);
    }

    @CacheEvict(cacheNames = "menu", key = "#id")
    public void deleteById(Long id) {
        clearCache();
        IService.super.deleteById(id);
    }

    @CacheEvict(cacheNames = "menus")
    public void clearCache() { }
}
