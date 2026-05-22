package com.habialtx3.ecommerce_be.model.category;

import com.habialtx3.ecommerce_be.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private UUID id;

    private String name;

    private String slug;

    private String description;

    private List<Product> products;
}
