package com.imagina.productsservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "units", nullable = false)
    private Integer units;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ProductDetails productDetails;

    public Product(Long id,
                   String name,
                   Integer code,
                   String description,
                   Integer units,
                   Double price,
                   Category category,
                   ProductDetails productDetails) {
        super.setId(id);
        this.name = name;
        this.code = code;
        this.description = description;
        this.units = units;
        this.price = price;
        this.category = category;
        this.productDetails = productDetails;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Product product)) {
            return false;
        }

        return Objects.equals(code, product.getCode());
    }

    @Override
    public String toString() {
        return "product ID: " + super.getId() + ", name: " + name;
    }
}
