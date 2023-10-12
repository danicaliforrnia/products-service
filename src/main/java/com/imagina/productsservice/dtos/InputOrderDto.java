package com.imagina.productsservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputOrderDto {
    @NotNull(message = "userFirstName can not be null")
    @NotBlank(message = "userFirstName can not be empty")
    @Size(min = 2, max = 50, message = "userFirstName must have from 2 to 50 characters")
    private String userFirstName;

    @NotNull(message = "userLastName can not be null")
    @NotBlank(message = "userLastName can not be empty")
    @Size(min = 2, max = 50, message = "userLastName must have from 2 to 50 characters")
    private String userLastName;

    @NotNull(message = "address can not be null")
    @NotBlank(message = "address can not be empty")
    @Size(min = 2, max = 200, message = "address must have from 2 to 200 characters")
    private String address;

    @NotNull(message = "orderItems can not be null")
    @NotEmpty(message = "orderItems cannot be empty")
    private List<InputOrderItemDto> orderItems;
}
