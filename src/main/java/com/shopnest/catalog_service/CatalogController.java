package com.shopnest.catalog_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CatalogController {
    private final List<Product> products = List.of(
            new Product("1", "Mechanichal Keyboar", 120.45),
            new Product("2", "Ergonomic mouse", 50.5)
    );

    @GetMapping
    public List<Product> getAllProducts(){
        return products;
    }

    public ResponseEntity<Product> getProductById(@PathVariable String id){
        return products.stream().filter(p -> p.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
}
