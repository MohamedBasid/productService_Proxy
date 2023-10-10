package com.example.productservice_proxy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    @GetMapping()
    public String getAllCategories() {
        return "Getting all categories";
    }

    @GetMapping("/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId) {
        return "Get products with category id as " + categoryId;
    }
}
