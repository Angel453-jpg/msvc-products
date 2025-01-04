package com.angel.springcloud.msvc.product.repositories;

import com.angel.springcloud.msvc.product.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
