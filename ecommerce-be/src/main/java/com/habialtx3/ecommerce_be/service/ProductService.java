package com.habialtx3.ecommerce_be.service;

import com.habialtx3.ecommerce_be.entity.Product;
import com.habialtx3.ecommerce_be.model.product.CreateProductRequest;
import com.habialtx3.ecommerce_be.model.product.ProductResponse;
import com.habialtx3.ecommerce_be.model.product.UpdateProductRequest;
import com.habialtx3.ecommerce_be.model.web.WebResponse;
import com.habialtx3.ecommerce_be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validation;

    private ProductResponse toProductResponse(Product response) {
        return ProductResponse.builder()
                .id(response.getId())
                .slug(response.getSlug())
                .description(response.getDescription())
                .name(response.getName())
                .weight(response.getWeight())
                .price(response.getPrice())
                .createdAt(response.getCreatedAt())
                .build();
    }

    @Transactional
    public ProductResponse create(CreateProductRequest request) {

        validation.validate(request);

        Product product = new Product();
        product.setName(request.getName());
        String generatedSlug = request.getName().trim().toLowerCase().replaceAll("\\s+", "-");
        product.setSlug(generatedSlug);
        product.setDescription(request.getDescription());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setWeight(request.getWeight());
        product.setStatus(request.getStatus());
        product.setCreatedAt(LocalDateTime.now());

        productRepository.save(product);

        return toProductResponse(product);
    }


    @Transactional(readOnly = true)
    public List<ProductResponse> list() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(
                product -> toProductResponse(product)
        ).toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse getBySlug(String slug) {
        Product product = productRepository.findBySlug(slug).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found")
        );

        return toProductResponse(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(String id) {
        Product product = productRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found")
        );

        return toProductResponse(product);
    }

    @Transactional
    public ProductResponse update(String id, UpdateProductRequest request) {

        validation.validate(request);

        Product product = productRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found")
        );

        if (Objects.nonNull(request.getName())) {
            product.setName(request.getName());
            String generatedSlug = request.getName().trim().toLowerCase().replaceAll("\\s+", "-");
            product.setSlug(generatedSlug);
        }

        if (Objects.nonNull(request.getDescription())) {
            product.setDescription(request.getDescription());
        }

        if (Objects.nonNull(request.getPrice())) {
            product.setPrice(request.getPrice());
        }

        if (Objects.nonNull(request.getWeight())) {
            product.setWeight(request.getWeight());
        }

        productRepository.save(product);

        return toProductResponse(product);
    }
}
