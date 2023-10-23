package com.imagina.productsservice.controllers;

import com.imagina.productsservice.services.CachingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cache")
public class CachingController {

    private final CachingService cachingService;

    public CachingController(CachingService cachingService) {
        this.cachingService = cachingService;
    }

    @DeleteMapping()
    public void evictAllCaches() {
        cachingService.evictAllCaches();
    }

}
