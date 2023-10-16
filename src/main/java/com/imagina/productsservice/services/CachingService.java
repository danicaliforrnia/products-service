package com.imagina.productsservice.services;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CachingService {

    private final CacheManager cacheManager;

    public CachingService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
