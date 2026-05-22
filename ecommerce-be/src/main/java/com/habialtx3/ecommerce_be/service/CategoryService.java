package com.habialtx3.ecommerce_be.service;

import com.habialtx3.ecommerce_be.entity.Category;
import com.habialtx3.ecommerce_be.model.category.CategoryResponse;
import com.habialtx3.ecommerce_be.model.category.CreateCategoryRequest;
import com.habialtx3.ecommerce_be.repository.CategoryRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepostiory categoryRepostiory;

    @Autowired
    private ValidationService validation;

    private CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .products(category.getProducts())
                .build();
    }

    public CategoryResponse create(CreateCategoryRequest request) {
        validation.validate(request);

        Category category = new Category();
        category.setName(request.getName());
        String generatedSlug = request.getName().trim().toLowerCase().replaceAll("\\s+", "-");
        category.setSlug(generatedSlug);
        category.setDescription(request.getDescription());

        categoryRepostiory.save(category);

        return toCategoryResponse(category);
    }
}
