package com.imagina.productsservice.repositories;

import com.imagina.productsservice.entities.Cart;
import com.imagina.productsservice.entities.Category;
import com.imagina.productsservice.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findFirstByUserIdAndStatus(Long userId, CartStatus status);

}
