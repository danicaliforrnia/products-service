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
public class InputCartItemDto {
    @NotNull(message = "units can not be null")
    @Positive(message = "units must be a positive number")
    private Integer units;

    @NotNull(message = "product can not be null")
    @Positive(message = "product must be a positive number")
    private Long product;
}
