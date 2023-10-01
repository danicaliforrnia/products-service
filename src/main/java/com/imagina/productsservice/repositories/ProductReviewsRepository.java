package com.imagina.productsservice.repositories;

import com.imagina.productsservice.entities.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductReviewsRepository extends MongoRepository<ProductReview, String> {
    List<ProductReview> findAllByProductId(Long productId);
}
