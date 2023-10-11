package com.imagina.productsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadProductDto {
    private Long id;
    private String name;
    private Integer code;
    private String description;
    private Integer units;
    private Double price;
    private ReadCategoryDto category;
    private ReadProductDetailsDto productDetails;
}
