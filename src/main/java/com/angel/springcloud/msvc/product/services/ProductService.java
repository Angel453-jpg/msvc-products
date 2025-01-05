package com.angel.springcloud.msvc.product.services;

import com.angel.libs.msvc.commons.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

}
