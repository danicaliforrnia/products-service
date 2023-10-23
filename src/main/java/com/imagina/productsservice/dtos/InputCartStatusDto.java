package com.imagina.productsservice.dtos;

import com.imagina.productsservice.enums.CartStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCartStatusDto {
    @NotNull(message = "status can not be null")
    private CartStatus status;
}
