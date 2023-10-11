package com.imagina.productsservice.repositories;

import com.imagina.productsservice.entities.Category;
import com.imagina.productsservice.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductsRepositoryIntegrationTests {
    Category category = new Category(1L, "CategoryA", "categoryA description");
    Product product = new Product(1L, "productA", 1000, "productA description", 10, 200D, category, null);

    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void shouldSaveProduct() {
        product.setCreatedBy("admin");
        product.setModifiedBy("admin");
        product.setCreatedDate(LocalDateTime.now());
        product.setModifiedDate(LocalDateTime.now());
        Product productSaved = productsRepository.save(product);
        assertNotNull(productSaved);
        assertEquals(productSaved.getName(), product.getName());
        assertEquals(productSaved.getCode(), product.getCode());
        assertEquals(productSaved.getDescription(), product.getDescription());
        assertEquals(productSaved.getPrice(), product.getPrice());
    }
}
