package com.example.productservice_proxy.DTOs;

import com.example.productservice_proxy.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private Categories category;
}
