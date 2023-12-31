package com.imagina.productsservice.dtos;

import com.imagina.productsservice.enums.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadCartDto {
    private Long id;
    private Long userId;
    private CartStatus status;
    private List<ReadCartItemDto> cartItems;
}
