package com.jhed.products.web.javaproducts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(length = 64)
    private String name;

    @Getter
    @Setter
    private float price;

    public Product(Integer id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
