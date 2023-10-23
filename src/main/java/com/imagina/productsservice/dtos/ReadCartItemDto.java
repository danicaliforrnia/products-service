package com.imagina.productsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadCartItemDto {
    private Integer units;
    private ReadProductDto product;
}
