package com.imagina.productsservice.services;

import com.imagina.productsservice.clients.OrdersServiceClient;
import com.imagina.productsservice.dtos.InputCartDto;
import com.imagina.productsservice.dtos.InputOrderDto;
import com.imagina.productsservice.dtos.InputOrderItemDto;
import com.imagina.productsservice.dtos.ReadCartDto;
import com.imagina.productsservice.entities.Cart;
import com.imagina.productsservice.entities.CartItem;
import com.imagina.productsservice.enums.CartStatus;
import com.imagina.productsservice.exceptions.CartExistsException;
import com.imagina.productsservice.exceptions.CartNotFoundException;
import com.imagina.productsservice.exceptions.CartNotOpenedException;
import com.imagina.productsservice.exceptions.ProductNotFoundException;
import com.imagina.productsservice.repositories.CartRepository;
import com.imagina.productsservice.repositories.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartsService {

    private final CartRepository cartRepository;

    private final ProductsRepository productsRepository;

    private final ModelMapper modelMapper;

    private final OrdersServiceClient ordersServiceClient;

    public CartsService(CartRepository cartRepository, ProductsRepository productsRepository, ModelMapper modelMapper, OrdersServiceClient ordersServiceClient) {
        this.cartRepository = cartRepository;
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
        this.ordersServiceClient = ordersServiceClient;
    }

    public ReadCartDto findByUserAndStatus(Long userId) {
        return modelMapper.map(
                cartRepository.findFirstByUserIdAndStatus(userId, CartStatus.OPEN).orElseThrow(CartNotFoundException::new),
                ReadCartDto.class
        );
    }

    public ReadCartDto create(Long userId, InputCartDto inputCartDto) {
        if (cartRepository.findFirstByUserIdAndStatus(userId, CartStatus.OPEN).isPresent()) {
            throw new CartExistsException();
        }


        Cart cart = modelMapper.map(inputCartDto, Cart.class);
        List<CartItem> cartItems = new ArrayList<>();

        inputCartDto.getCartItems().forEach(inputCartItem -> {
            var product = productsRepository.findById(inputCartItem.getProduct()).orElseThrow(ProductNotFoundException::new);
            CartItem cartItem = modelMapper.map(inputCartItem, CartItem.class);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        });

        cart.setCartItems(cartItems);
        cart.setUserId(userId);
        cart.setStatus(CartStatus.OPEN);

        cartRepository.save(cart);

        return modelMapper.map(cart, ReadCartDto.class);
    }

    public void updateStatus(Long cartId, CartStatus cartStatus) {
        var cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);

        if (!cart.getStatus().equals(CartStatus.OPEN)) {
            throw new CartNotOpenedException();
        }

        cart.setStatus(cartStatus);

        cartRepository.save(cart);

        if (cart.getStatus().equals(CartStatus.PROCESSED)) {
            var inputOrderDto = new InputOrderDto("test", "test", "test", new ArrayList<>());
            cart.getCartItems().forEach(cartItem -> {
                var inputOrderItemDto = new InputOrderItemDto(cartItem.getProduct().getName(), cartItem.getUnits(), cartItem.getProduct().getPrice());
                inputOrderDto.getOrderItems().add(inputOrderItemDto);
            });
            ordersServiceClient.createOrder(inputOrderDto);
        }
    }
}
