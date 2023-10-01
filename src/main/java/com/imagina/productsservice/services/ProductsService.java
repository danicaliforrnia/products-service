package com.imagina.productsservice.services;

import com.imagina.productsservice.dtos.InputProductDto;
import com.imagina.productsservice.dtos.ReadProductDto;
import com.imagina.productsservice.entities.Category;
import com.imagina.productsservice.entities.Product;
import com.imagina.productsservice.exceptions.CategoryNotFoundException;
import com.imagina.productsservice.exceptions.ProductNotFoundException;
import com.imagina.productsservice.repositories.CategoryRepository;
import com.imagina.productsservice.repositories.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public ProductsService(ProductsRepository productsRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public Page<ReadProductDto> findAll(String name, int page, int size) {
        Page<Product> productsFiltered;
        var pageable = PageRequest.of(page, size);

        if (name != null && !name.isEmpty()) {
            productsFiltered = productsRepository.findAllByNameContainingIgnoreCase(name, pageable);
        } else {
            productsFiltered = productsRepository.findAll(pageable);
        }

        return productsFiltered.map(product -> modelMapper.map(product, ReadProductDto.class));
    }

    @Transactional(readOnly = true)
    public ReadProductDto findById(Long id) {
        return modelMapper.map(
                productsRepository.findById(id).orElseThrow(ProductNotFoundException::new),
                ReadProductDto.class
        );
    }

    @Transactional
    public ReadProductDto create(InputProductDto inputProductDto) {
        var product = modelMapper.map(inputProductDto, Product.class);
        product.setCategory(findCategory(inputProductDto.getCategory()));
        productsRepository.save(product);
        return modelMapper.map(product, ReadProductDto.class);
    }

    @Transactional
    public ReadProductDto update(Long id, InputProductDto inputProductDto) {
        var product = findProductById(id);
        modelMapper.map(inputProductDto, product);
        product.setCategory(findCategory(inputProductDto.getCategory()));
        productsRepository.save(product);
        return modelMapper.map(product, ReadProductDto.class);
    }

    @Transactional
    public void delete(Long id) {
        productsRepository.findById(id).ifPresentOrElse(productsRepository::delete, () -> {
            throw new ProductNotFoundException();
        });
    }

    private Product findProductById(Long id) {
        return productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                CategoryNotFoundException::new
        );
    }
}
