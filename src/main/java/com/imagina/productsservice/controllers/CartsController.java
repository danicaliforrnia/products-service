package com.imagina.productsservice.controllers;

import com.imagina.productsservice.dtos.*;
import com.imagina.productsservice.services.CartsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carts")
public class CartsController {

    private final CartsService cartsService;

    public CartsController(CartsService cartsService) {
        this.cartsService = cartsService;
    }

    @GetMapping(produces = "application/json")
    public ReadCartDto findByUserIdAndStatus(@RequestParam(name = "userId") String userId) {
        return cartsService.findByUserAndStatus(Long.parseLong(userId));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ReadCartDto create(@Valid @RequestBody InputCartDto inputCartDto, @RequestParam(name = "userId") String userId) {
        return cartsService.create(Long.parseLong(userId), inputCartDto);
    }

    @PatchMapping(path = "{id}", consumes = "application/json")
    public void updateStatus(@PathVariable Long id, @Valid @RequestBody InputCartStatusDto inputCartStatusDto) {
        cartsService.updateStatus(id, inputCartStatusDto.getStatus());
    }
}
