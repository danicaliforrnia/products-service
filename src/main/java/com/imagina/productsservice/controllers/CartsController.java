package com.imagina.productsservice.controllers;

import com.imagina.productsservice.services.CartsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carts")
public class CartsController {

    private final CartsService cartsService;

    public CartsController(CartsService cartsService) {
        this.cartsService = cartsService;
    }
}
