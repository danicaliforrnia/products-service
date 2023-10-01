package com.imagina.productsservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "product_reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {
    @Id
    private String id;

    @Field("product_id")
    private Long productId;

    @Field("rating")
    private Integer rating;

    @Field("comment")
    private String comment;
}