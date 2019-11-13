package com.bjit.spring.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CacheController {
	@Autowired
    private CacheManager cacheManager;      // autowire cache manager
    // clear all cache using cache manager
    @GetMapping("clearCache")
    public Object clearCache(){
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();            // clear cache by name
        }
        return cacheManager.getCacheNames();
    }
    
    @GetMapping("getCacheNames")
    public Object getCacheNames(){
        return cacheManager.getCacheNames();
    }
    
    @GetMapping("getCacheNames/{cacheName}")
    public Object getCacheNames(@PathVariable("cacheName") String cacheName){
        return cacheManager.getCache(cacheName);
    }

}