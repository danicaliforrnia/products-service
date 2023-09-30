package com.imagina.productsservice.controllers;

import com.imagina.productsservice.dtos.ReadCategoryDto;
import com.imagina.productsservice.services.CategoriesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping(produces = "application/json")
    public List<ReadCategoryDto> findAll() {
        return categoriesService.findAll();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public ReadCategoryDto findById(@PathVariable Long id) {
        return categoriesService.findById(id);
    }

}
