package com.imagina.productsservice.services;

import com.imagina.productsservice.repositories.CartRepository;
import com.imagina.productsservice.repositories.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CartsService {

    private final CartRepository cartRepository;

    private final ProductsRepository productsRepository;

    private final ModelMapper modelMapper;

    public CartsService(CartRepository cartRepository, ProductsRepository productsRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
    }
}
