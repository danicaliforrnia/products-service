package com.imagina.productsservice.services;

import com.imagina.productsservice.dtos.InputProductReviewDto;
import com.imagina.productsservice.dtos.ReadProductReviewDto;
import com.imagina.productsservice.entities.ProductReview;
import com.imagina.productsservice.repositories.ProductReviewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductReviewsService {
    private final ProductReviewsRepository productReviewsRepository;

    private final ModelMapper modelMapper;

    public ProductReviewsService(ProductReviewsRepository productReviewsRepository, ModelMapper modelMapper) {
        this.productReviewsRepository = productReviewsRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReadProductReviewDto> findAllByProductId(Long productId) {
        return productReviewsRepository
                .findAllByProductId(productId)
                .stream()
                .map(productReview -> modelMapper.map(productReview, ReadProductReviewDto.class))
                .collect(Collectors.toList());
    }

    public ReadProductReviewDto create(InputProductReviewDto inputProductReviewDto) {
        var productReview = modelMapper.map(inputProductReviewDto, ProductReview.class);
        productReviewsRepository.save(productReview);
        return modelMapper.map(productReview, ReadProductReviewDto.class);
    }

}
