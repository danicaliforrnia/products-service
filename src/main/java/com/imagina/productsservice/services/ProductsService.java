package com.imagina.productsservice.services;

import com.imagina.productsservice.dtos.InputProductDto;
import com.imagina.productsservice.dtos.ReadProductDto;
import com.imagina.productsservice.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    List<ReadProductDto> products = new ArrayList<>(
            List.of(
                    new ReadProductDto(1L, "iPhone 15", "New iPhone 15 1TB", 1500D),
                    new ReadProductDto(2L, "iPhone 13 pro MAX", "iPhone 13 pro MAX 1TB", 1300D),
                    new ReadProductDto(3L, "Nintendo Switch OLED", "Nitendo Switch OLED Version", 300D),
                    new ReadProductDto(4L, "1984 - George Orwell", "George Orwell book", 20D)
            )
    );

    public List<ReadProductDto> findAll(String name, int size) {
        var productsFiltered = products;

        if (name != null && !name.isEmpty()) {
            productsFiltered = products
                    .stream()
                    .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        }

        if (productsFiltered.size() > size) {
            return productsFiltered.subList(0, size);
        }

        return productsFiltered;
    }

    public ReadProductDto findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    public ReadProductDto create(InputProductDto inputProductDto) {
        var newProduct = new ReadProductDto();
        newProduct.setId(System.currentTimeMillis());
        newProduct.setName(inputProductDto.getName());
        newProduct.setDescription(inputProductDto.getDescription());
        newProduct.setPrice(inputProductDto.getPrice());
        products.add(newProduct);
        return newProduct;
    }

    public ReadProductDto update(Long id, InputProductDto inputProductDto) {
        var product = findById(id);
        product.setName(inputProductDto.getName());
        product.setDescription(inputProductDto.getDescription());
        product.setPrice(inputProductDto.getPrice());
        return product;
    }

    public void delete(Long id) {
        var product = findById(id);
        products.remove(product);
    }
}
