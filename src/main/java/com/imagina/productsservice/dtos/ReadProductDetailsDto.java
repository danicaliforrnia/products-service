package com.imagina.productsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadProductDetailsDto {
    private Long id;
    private String color;
    private Integer height;
    private Integer weight;
}
