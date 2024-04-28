package com.example.productservice_proxy.services;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.models.Products;
import com.example.productservice_proxy.repositories.ProductServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "our")
public class OurProductService implements  IProductService{

    @Autowired
    ProductServiceRepository repo;

    @Override
    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Optional<Products> getSingleProduct(Long productId) {
        return repo.findById(productId);
    }

    @Override
    public Products addNewProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Products updateProduct(Long productId, Products product) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
