package com.project.products.springboot_products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.products.springboot_products.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
