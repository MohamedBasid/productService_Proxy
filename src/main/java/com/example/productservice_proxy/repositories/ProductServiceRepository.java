package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductServiceRepository extends JpaRepository<Products, Long> {

}
