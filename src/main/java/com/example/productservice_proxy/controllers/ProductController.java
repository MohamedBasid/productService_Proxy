package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.DTOs.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("")
    public String getAllProducts() {
        return "Getting all products";
    }
    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long productId) {
        return "Getting single product " + productId;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto) {
        return "Adding new product " + productDto;
    }

    @PutMapping("/{Id}")
    public String updateProduct(@PathVariable("Id") Long productId) {
        return "Updating product " + productId;
    }

    @PatchMapping("/{Id}")
    public String patchProduct(@PathVariable("Id") Long productId) {
        return "Patching product " + productId;
    }

    @DeleteMapping("/{Id}")
    public String deleteProduct(@PathVariable("Id") Long productId) {
        return "Deleting product " + productId;
    }
}
