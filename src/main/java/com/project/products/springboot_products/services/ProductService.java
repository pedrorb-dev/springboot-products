package com.project.products.springboot_products.services;

import java.util.List;

import com.project.products.springboot_products.entities.Category;
import com.project.products.springboot_products.entities.Product;

public interface ProductService {

    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    Product update(Long id, Product product);

    Product addCategory(Long id, Category category);
}
