package com.angel.springcloud.msvc.product.services;

import com.angel.springcloud.msvc.product.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

}
