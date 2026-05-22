package com.habialtx3.ecommerce_be.model.product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    @NotBlank(message = "Nama produk tidak boleh kosong")
    private String name;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    private String description;

    @NotNull(message = "Harga tidak boleh kosong")
    @DecimalMin(value = "0.0", inclusive = false, message = "Harga harus lebih besar dari 0 Rp.")
    private BigDecimal price;

    @NotNull(message = "Berat tidak boleh kosong")
    @Min(value = 1, message = "Berat minimal adalah 1 gram")
    private Integer weight;

    @NotNull(message = "Category tidak boleh kosong")
    private String category;

    private String status;
}
