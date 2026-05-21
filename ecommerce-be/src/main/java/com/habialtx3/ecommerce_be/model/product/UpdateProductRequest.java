package com.habialtx3.ecommerce_be.model.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateProductRequest {

    private String name;


    private String description;


    private BigDecimal price;


    private Integer weight;


    private String status;
}
