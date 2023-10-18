package com.example.productservice_proxy.services;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.models.Products;

import java.util.List;

public interface IProductService {
    List<Products> getAllProducts();
    Products getSingleProduct(Long productId);
    Products addNewProduct(ProductDto productDto);
    Products updateProduct(Long productId, Products product);
    String deleteProduct(Long productId);
}
