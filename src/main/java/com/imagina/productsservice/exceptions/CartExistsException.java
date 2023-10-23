package com.imagina.productsservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CartExistsException extends RuntimeException {
    public CartExistsException() {
        super("Cart exists");
    }

    public CartExistsException(String message) {
        super(message);
    }
}
