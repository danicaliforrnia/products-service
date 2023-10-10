package com.imagina.productsservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(Long id, String name, String description) {
        super.setId(id);
        this.name = name;
        this.description = description;
        products = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Category product)) {
            return false;
        }

        return Objects.equals(name, product.getName());
    }

    @Override
    public String toString() {
        return "Category ID: " + super.getId() + ", name: " + name;
    }

}
