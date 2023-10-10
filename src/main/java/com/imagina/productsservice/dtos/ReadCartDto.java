package com.imagina.productsservice.dtos;

import com.imagina.productsservice.entities.CartItem;
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
    private List<CartItem> cartItems;
}
