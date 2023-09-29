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
    private String description;
    private Double price;
}
