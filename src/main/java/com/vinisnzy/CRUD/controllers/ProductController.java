package com.vinisnzy.CRUD.controllers;

import com.vinisnzy.CRUD.domain.product.Product;
import com.vinisnzy.CRUD.domain.product.ProductRepository;
import com.vinisnzy.CRUD.domain.product.RequestProductDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity <List<Product>> getAllProducts() {
        return ResponseEntity.ok(repository.findAllByActiveTrue());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid RequestProductDTO data) {
        Product product = new Product(data);
        repository.save(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid RequestProductDTO data) {
        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice(data.price());
            return ResponseEntity.ok(product);
        }
        throw new EntityNotFoundException();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        }
        throw new EntityNotFoundException();
    }
}
