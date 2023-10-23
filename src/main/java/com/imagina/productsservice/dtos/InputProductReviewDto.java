package com.imagina.productsservice.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductReviewDto {

    @NotNull(message = "El campo productId no puede ser nulo")
    @Positive
    private Long productId;

    @NotNull(message = "El campo comment no puede ser nulo")
    @NotBlank(message = "El campo comment no puede estar en blanco")
    @Size(min = 2, max = 200, message = "El campo comment debe tener entre 2 y 50 caracteres")
    private String comment;

    @NotNull(message = "El campo price no puede ser nulo")
    @Max(value = 5, message = "El campo rating solo puede ser 5 como máximo")
    @PositiveOrZero
    private Integer rating;
}
