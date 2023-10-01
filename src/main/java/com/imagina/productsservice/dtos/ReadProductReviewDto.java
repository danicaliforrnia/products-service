package com.imagina.productsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadProductReviewDto {

    private String id;

    private Long productId;

    private Integer rating;

    private String comment;

}
