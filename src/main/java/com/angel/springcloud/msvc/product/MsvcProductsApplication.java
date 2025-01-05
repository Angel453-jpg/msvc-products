package com.angel.springcloud.msvc.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.angel.libs.msvc.commons.entities", "com.angel.libs.msvc.product.entities"})
public class MsvcProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcProductsApplication.class, args);
    }

}
