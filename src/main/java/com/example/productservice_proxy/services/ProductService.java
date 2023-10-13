package com.example.productservice_proxy.services;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Products;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Products> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products",
                ProductDto[].class);

        List<Products> answer = new ArrayList<>();

        for (ProductDto productDto: productDtos.getBody()) {
            Products product = new Products();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Categories category = new Categories();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            answer.add(product);
        }
        return answer;
    }

    @Override
    public Products getSingleProduct(Long productId) {

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        ResponseEntity<ProductDto> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                    ProductDto.class, productId);

        if(productDto == null) return null;

        Products product = getProduct(productDto.getBody());
        return product;
    }

    @Override
    public Products addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        restTemplate.postForEntity("https://fakestoreapi.com/products",
                productDto, ProductDto.class);

        Products product = getProduct(productDto);
        return product;
    }

    @Override
    public String updateProduct(Long productId) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }

    private Products getProduct(ProductDto productDto) {
        Products product = new Products();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }
}
