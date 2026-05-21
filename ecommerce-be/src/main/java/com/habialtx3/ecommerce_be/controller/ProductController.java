package com.habialtx3.ecommerce_be.controller;

import com.habialtx3.ecommerce_be.model.product.CreateProductRequest;
import com.habialtx3.ecommerce_be.model.product.ProductResponse;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
            path = "/api/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> create(@RequestBody CreateProductRequest request) {
        ProductResponse response = productService.create(request);

        return WebResponse.<ProductResponse>builder()
                .data(response)
                .message("Product created successfully")
                .build();
    }

}
