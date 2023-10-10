package com.imagina.productsservice.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCartDto {
    @NotNull(message = "cartItems can not be null")
    @NotEmpty(message = "cartItems cannot be empty")
    private List<InputCartItemDto> cartItems;
}
