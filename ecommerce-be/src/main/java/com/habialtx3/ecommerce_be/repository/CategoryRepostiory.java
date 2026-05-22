package com.habialtx3.ecommerce_be.repository;

import com.habialtx3.ecommerce_be.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CategoryRepostiory extends JpaRepository<Category, UUID> {
}
