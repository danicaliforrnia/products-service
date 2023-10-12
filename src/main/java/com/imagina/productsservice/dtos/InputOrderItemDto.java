package com.imagina.productsservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputOrderItemDto {
    @NotNull(message = "productName can not be null")
    @NotBlank(message = "productName can not be empty")
    @Size(min = 2, max = 50, message = "productName must have from 2 to 50 characters")
    private String productName;

    @NotNull(message = "units can not be null")
    @Positive(message = "units must be a positive number")
    private Integer units;

    @NotNull(message = "price can not be null")
    @Positive(message = "price must be a positive number")
    private Double price;
}
