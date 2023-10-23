package com.imagina.productsservice.repositories;

import com.imagina.productsservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameContainingIgnoreCase(String name);

    Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
