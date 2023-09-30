package com.imagina.productsservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {
    @NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be empty")
    @Size(min = 2, max = 50, message = "name must have from 2 to 50 characters")
    private String name;

    @NotNull(message = "description can not be null")
    @NotBlank(message = "description can not be empty")
    @Size(min = 2, max = 200, message = "description must have from 2 to 200 characters")
    private String description;

    @NotNull(message = "price can not be null")
    @Positive(message = "price must be a positive number")
    private Double price;

    @NotNull(message = "category can not be null")
    @Positive(message = "category must be a positive number")
    private Long category;
}
