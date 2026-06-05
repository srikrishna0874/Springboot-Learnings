package com.codingshuttle.jpa_learnings.controllers;

import com.codingshuttle.jpa_learnings.entities.ProductEntity;
import com.codingshuttle.jpa_learnings.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productRepository.findByOrderByPrice();
    }

}
