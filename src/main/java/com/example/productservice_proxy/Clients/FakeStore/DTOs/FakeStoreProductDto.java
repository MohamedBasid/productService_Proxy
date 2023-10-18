package com.example.productservice_proxy.Clients.FakeStore.DTOs;

import com.example.productservice_proxy.Clients.IClientProductDto;
import com.example.productservice_proxy.DTOs.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private RatingDto rating;
}
