package com.imagina.productsservice.controllers;

import com.imagina.productsservice.dtos.InputProductDto;
import com.imagina.productsservice.dtos.ReadProductDto;
import com.imagina.productsservice.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping(produces = "application/json")
    @Cacheable("products")
    public Page<ReadProductDto> findAll(@RequestParam(name = "name", required = false) String name,
                                        @RequestParam(name = "page", required = false, defaultValue = "0") String page,
                                        @RequestParam(name = "size", required = false, defaultValue = "5") String size) {
        return productsService.findAll(name, Integer.parseInt(page), Integer.parseInt(size));
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public ReadProductDto findById(@PathVariable Long id) {
        return productsService.findById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @CacheEvict(cacheNames = "products", allEntries = true)
    public ReadProductDto create(@Valid @RequestBody InputProductDto inputProductDto) {
        return productsService.create(inputProductDto);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @CacheEvict(cacheNames = "products", allEntries = true)
    public ReadProductDto update(@PathVariable Long id, @Valid @RequestBody InputProductDto inputProductDto) {
        return productsService.update(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void delete(@PathVariable Long id) {
        productsService.delete(id);
    }
}
