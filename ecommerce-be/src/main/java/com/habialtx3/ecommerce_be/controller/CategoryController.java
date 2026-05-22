package com.habialtx3.ecommerce_be.controller;

import com.habialtx3.ecommerce_be.model.category.CategoryResponse;
import com.habialtx3.ecommerce_be.model.category.CreateCategoryRequest;
import com.habialtx3.ecommerce_be.model.category.UpdateCategoryRequest;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
                .message("Category created successfully")
                .build();
    }

    @GetMapping
    WebResponse<List<CategoryResponse>> list() {
        List<CategoryResponse> response = categoryService.list();
        return WebResponse.<List<CategoryResponse>>builder()
                .data(response)
                .build();
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebResponse<CategoryResponse> create(@PathVariable String id, @RequestBody UpdateCategoryRequest request) {
        CategoryResponse response = categoryService.update(id, request);

        return WebResponse.<CategoryResponse>builder()
                .data(response)
                .message("Category updated successfully")
                .build();
    }

}
