package com.imagina.productsservice.services;

import com.imagina.productsservice.dtos.ReadCategoryDto;
import com.imagina.productsservice.dtos.ReadProductDto;
import com.imagina.productsservice.exceptions.CategoryNotFoundException;
import com.imagina.productsservice.exceptions.ProductNotFoundException;
import com.imagina.productsservice.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoriesService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReadCategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, ReadCategoryDto.class)).collect(Collectors.toList());
    }

    public ReadCategoryDto findById(Long id) {
        return modelMapper.map(
                categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new),
                ReadCategoryDto.class
        );
    }
}
