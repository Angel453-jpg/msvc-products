package com.angel.springcloud.msvc.product.controllers;

import com.angel.libs.msvc.commons.entities.Product;
import com.angel.springcloud.msvc.product.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {

        Optional<Product> productOptional = productService.findById(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "Producto con ID: " + id + " no encontrado"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {

        Optional<Product> productOptional = findProductById(id);

        if (productOptional.isPresent()) {

            Product productDb = productOptional.orElseThrow();
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setCreateAt(product.getCreateAt());

            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDb));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "Producto con ID: " + id + " no encontrado!"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Product> productOptional = findProductById(id);

        if (productOptional.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Producto con id: " + id + " eliminado correctamente!"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "Producto con ID: " + id + " no encontrado!"));

    }

    private Optional<Product> findProductById(Long id) {
        return productService.findById(id);
    }


}
