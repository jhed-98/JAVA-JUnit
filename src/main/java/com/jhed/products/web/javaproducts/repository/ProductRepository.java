package com.jhed.products.web.javaproducts.repository;

import com.jhed.products.web.javaproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findByName(String name);
}
