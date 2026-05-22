package com.habialtx3.ecommerce_be.controller;

import com.habialtx3.ecommerce_be.entity.User;
import com.habialtx3.ecommerce_be.model.product.CreateProductRequest;
import com.habialtx3.ecommerce_be.model.product.ProductResponse;
import com.habialtx3.ecommerce_be.model.product.UpdateProductRequest;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> create(User user, @RequestBody CreateProductRequest request) {
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


    @GetMapping(
            path = "/{slug}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> get(@PathVariable String slug) {
        ProductResponse response = productService.getBySlug(slug);

        return WebResponse.<ProductResponse>builder()
                .data(response)
                .message("Product " + slug + " has been found")
                .build();
    }

    @GetMapping(
            path = "/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> getById(@PathVariable String id) {
        ProductResponse response = productService.getById(id);

        return WebResponse.<ProductResponse>builder()
                .data(response)
                .message("Product " + id + " has been found")
                .build();
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> update(@PathVariable String id, @RequestBody UpdateProductRequest request) {
        ProductResponse response = productService.update(id, request);

        return WebResponse.<ProductResponse>builder()
                .data(response)
                .message("Product Updated successfully")
                .build();
    }

    @DeleteMapping(
            path = "/{id}"
    )
    WebResponse<String> delete(@PathVariable String id){
        productService.delete(id);

        return WebResponse.<String>builder()
                .message("Product deleted Successfully")
                .build();
    }

}
