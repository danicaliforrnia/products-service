package com.imagina.productsservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CartNotOpenedException extends RuntimeException {
    public CartNotOpenedException() {
        super("Cart is not opened");
    }

    public CartNotOpenedException(String message) {
        super(message);
    }
}
