package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.DTOs.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Products;
import com.example.productservice_proxy.services.FakeStoreProductService;
import com.example.productservice_proxy.services.IProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;
    public ProductController(@Qualifier("our") IProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Products>> getAllProducts() {

        List<Products> productsList = productService.getAllProducts();

        ResponseEntity<List<Products>> listResponseEntity = new ResponseEntity<>(productsList,
                HttpStatus.OK);
        return listResponseEntity;

    }
    @GetMapping("/{id}")
    public ResponseEntity<Products> getSingleProduct(@PathVariable("id") Long productId) {
        //try{
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("auth-token", "It's Basid");
            Products product = productService.getSingleProduct(productId).get();
            if(product == null) throw new IllegalArgumentException("Given id does not exist");

            ResponseEntity<Products> productsResponseEntity = new ResponseEntity<>(product,
                    /*headers,*/ HttpStatus.OK);
            return productsResponseEntity;
        /*
        }

        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        */
    }

    @PostMapping()
    public ResponseEntity<Products> addNewProduct(@RequestBody ProductDto productDto) {

        ResponseEntity<Products> responseEntity = new ResponseEntity<>(this.productService.addNewProduct(productDto)
                , HttpStatus.OK);

        return responseEntity;
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<Products> patchProduct(@PathVariable("Id") Long productId, @RequestBody ProductDto productDto) {

        Products product = new Products();
        product.setId(productDto.getId());
        product.setCategory(new Categories());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        ResponseEntity<Products> responseEntity = new ResponseEntity<>(
                this.productService.updateProduct(productId, product),
                HttpStatus.OK
        );

        return responseEntity;
    }

    @PutMapping("/{Id}")
    public String updateProduct(@PathVariable("Id") Long productId) {
        return "updating (replacing) product " + productId;
    }

    @DeleteMapping("/{Id}")
    public String deleteProduct(@PathVariable("Id") Long productId) {
        return "Deleting product " + productId;
    }
}
