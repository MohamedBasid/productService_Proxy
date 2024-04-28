package com.example.productservice_proxy.services;

import com.example.productservice_proxy.Clients.FakeStore.DTOs.FakeStoreProductDto;
import com.example.productservice_proxy.Clients.FakeStore.FakeStoreClient;
import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Products;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductService implements IProductService {

   @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    /*
    //ProductService(RestTemplateBuilder restTemplateBuilder) {
    //    this.restTemplateBuilder = restTemplateBuilder;
    //}
    */
    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public List<Products> getAllProducts() {
        /*RestTemplate restTemplate = this.restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products",
                ProductDto[].class);*/

        List<FakeStoreProductDto> fakeStoreProductDto = fakeStoreClient.getAllProducts();

        List<Products> answer = new ArrayList<>();

        for (FakeStoreProductDto productDto: fakeStoreProductDto) {
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
    public Optional<Products> getSingleProduct(Long productId) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                    FakeStoreProductDto.class, productId);

        if(fakeStoreProductDtoResponseEntity == null) return null;

        Products product = getProduct(fakeStoreProductDtoResponseEntity.getBody());
        return Optional.of(product);
    }

    @Override
    public Products addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        restTemplate.postForEntity("https://fakestoreapi.com/products",
                productDto, ProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(productDto.getId());
        fakeStoreProductDto.setDescription(productDto.getDescription());
        fakeStoreProductDto.setImage(productDto.getImage());
        fakeStoreProductDto.setPrice(productDto.getPrice());
        fakeStoreProductDto.setTitle(productDto.getTitle());
        fakeStoreProductDto.setCategory(productDto.getCategory());

        Products product = getProduct(fakeStoreProductDto);
        return product;
    }

    @Override
    public Products updateProduct(Long productId, Products product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> responseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return getProduct(responseEntity.getBody());
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate = restTemplateBuilder.requestFactory(
//                HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }

    private Products getProduct(FakeStoreProductDto productDto) {
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
