package com.imagina.productsservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "product_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
    @Id
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Override
    public int hashCode() {
        return Objects.hash(product.getCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof ProductDetails productDetails)) {
            return false;
        }

        return Objects.equals(product.getCode(), productDetails.getProduct().getCode());
    }

    @Override
    public String toString() {
        return "product ID: " + id;
    }
}
