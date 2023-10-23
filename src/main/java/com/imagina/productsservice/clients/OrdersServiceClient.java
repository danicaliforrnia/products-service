package com.imagina.productsservice.clients;

import com.imagina.productsservice.dtos.InputOrderDto;
import com.imagina.productsservice.dtos.ReadOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orders-service", path = "/orders-service/api/orders")
public interface OrdersServiceClient {

    @PostMapping(consumes = "application/json", produces = "application/json")
    ReadOrderDto createOrder(@RequestBody InputOrderDto inputOrderDto);
}
