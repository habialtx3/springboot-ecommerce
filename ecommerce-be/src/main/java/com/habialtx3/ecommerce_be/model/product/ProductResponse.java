package com.habialtx3.ecommerce_be.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {


    private UUID id;

//    private Category category;

    private String name;

    private String slug;

    private String description;

    private BigDecimal price;

    private Integer weight; // Dalam gram

    private String status; // Menggunakan String (DRAFT, ACTIVE, ARCHIVED) atau Enum

    private LocalDateTime createdAt = LocalDateTime.now();
}
