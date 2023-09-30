package com.imagina.productsservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_details")
@Data
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
}
