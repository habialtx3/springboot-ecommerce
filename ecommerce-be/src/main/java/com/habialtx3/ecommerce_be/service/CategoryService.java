package com.habialtx3.ecommerce_be.service;

import com.habialtx3.ecommerce_be.entity.Category;
import com.habialtx3.ecommerce_be.model.category.CategoryResponse;
import com.habialtx3.ecommerce_be.model.category.CreateCategoryRequest;
import com.habialtx3.ecommerce_be.model.category.UpdateCategoryRequest;
import com.habialtx3.ecommerce_be.repository.CategoryRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
                .s
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

    public List<CategoryResponse> list() {
        List<Category> categories = categoryRepostiory.findAll();

        return categories.stream().map(
                category -> toCategoryResponse(category)
        ).toList();
    }

    public CategoryResponse update(String id, UpdateCategoryRequest request) {
        validation.validate(request);

        Category category =  categoryRepostiory.findById(UUID.fromString(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found")
        );

        if (Objects.nonNull(request.getName())) {
            category.setName(request.getName());
            String generatedSlug = request.getName().trim().toLowerCase().replaceAll("\\s+", "-");
            category.setSlug(generatedSlug);
        }

        if (Objects.nonNull(request.getDescription())) {
            category.setDescription(request.getDescription());
        }

        categoryRepostiory.save(category);

        return toCategoryResponse(category);
    }

    public void delete(String id) {
        Category category =  categoryRepostiory.findById(UUID.fromString(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found")
        );

        categoryRepostiory.delete(category);
    }
}
