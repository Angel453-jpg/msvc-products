package com.angel.springcloud.msvc.product.controllers;

import com.angel.springcloud.msvc.product.entities.Product;
import com.angel.springcloud.msvc.product.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    public ResponseEntity<?> list() {

        List<Product> products = productService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Productos listados correctamente!");
        response.put("products", products);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {

        Optional<Product> productOptional = productService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (productOptional.isPresent()) {
            response.put("message", "Producto encontrado correctamente!");
            response.put("product", productOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", "Producto con ID: " + id + " no encontrado!"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {

        Product productNew = productService.save(product);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Producto creado correctamente!");
        response.put("product", productNew);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {

        Optional<Product> productOptional = findProductById(id);
        Map<String, Object> response = new HashMap<>();

        if (productOptional.isPresent()) {

            Product productDb = productOptional.orElseThrow();
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setCreateAt(product.getCreateAt());
            productService.save(productDb);

            response.put("message", "Producto actualizado correctamente!");
            response.put("product", productDb);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Producto con ID: " + id + " no encontrado!"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Product> productOptional = findProductById(id);

        if (productOptional.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("message", "Producto con id: " + id + " eliminado correctamente!"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Producto con ID: " + id + " no encontrado!"));

    }

    private Optional<Product> findProductById(Long id) {
        return productService.findById(id);
    }


}
