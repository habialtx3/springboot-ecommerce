package com.habialtx3.ecommerce_be.controller;

import com.habialtx3.ecommerce_be.model.product.CreateProductRequest;
import com.habialtx3.ecommerce_be.model.product.ProductResponse;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
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

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> list() {
        List<ProductResponse> responses = productService.list();

        return WebResponse.<List<ProductResponse>>builder()
                .data(responses)
                .build();
    }

}
