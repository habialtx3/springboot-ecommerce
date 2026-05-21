package com.habialtx3.ecommerce_be.repository;

import com.habialtx3.ecommerce_be.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
