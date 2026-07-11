package com.project.products.springboot_products.controllers;

import com.project.products.springboot_products.entities.Category;
import com.project.products.springboot_products.entities.Product;
import com.project.products.springboot_products.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')")
    public List<Product> getAll() {
        System.out.println("Permisos del usuario: " +
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.GONE).body(service.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result) {
        if(result.hasErrors()) {
            return validated(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Product product, BindingResult result) {
        if(result.hasErrors()) {
            return validated(result);
        }
        return ResponseEntity.status(HttpStatus.GONE).body(service.update(id, product));
    }

    @PatchMapping("/category/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')")
    public ResponseEntity<?> addCategory(@PathVariable Long id, @Valid @RequestBody Category category, BindingResult result) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addCategory(id, category));
    }

    private ResponseEntity<?> validated(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
