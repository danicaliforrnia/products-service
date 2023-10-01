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
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private Integer code;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ProductDetails productDetails;

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
