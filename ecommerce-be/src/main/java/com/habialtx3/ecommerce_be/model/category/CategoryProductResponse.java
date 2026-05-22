package com.habialtx3.ecommerce_be.model.category;

import com.habialtx3.ecommerce_be.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryProductResponse {
    private UUID id;

    private String name;

    private String slug;

    private String description;
    
    private List<Product> products;
}
