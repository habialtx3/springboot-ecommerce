package com.habialtx3.ecommerce_be.controller;

import com.habialtx3.ecommerce_be.model.category.CategoryResponse;
import com.habialtx3.ecommerce_be.model.category.CreateCategoryRequest;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebResponse<CategoryResponse> create(@RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryService.create(request);

        return WebResponse.<CategoryResponse>builder()
                .data(response)
                .build();
    }

}
