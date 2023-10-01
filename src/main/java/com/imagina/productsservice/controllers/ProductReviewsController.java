package com.imagina.productsservice.controllers;

import com.imagina.productsservice.dtos.InputProductReviewDto;
import com.imagina.productsservice.dtos.ReadProductReviewDto;
import com.imagina.productsservice.services.ProductReviewsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ProductReviewsController {

    private final ProductReviewsService productReviewsService;

    public ProductReviewsController(ProductReviewsService productReviewsService) {
        this.productReviewsService = productReviewsService;
    }

    @GetMapping(path = "/{productId}", produces = "application/json")
    public List<ReadProductReviewDto> findAllByProductId(@PathVariable Long productId) {
        return productReviewsService.findAllByProductId(productId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ReadProductReviewDto create(@RequestBody @Valid InputProductReviewDto inputProductReviewDto) {
        return productReviewsService.create(inputProductReviewDto);
    }

}
