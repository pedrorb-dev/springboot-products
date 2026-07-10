package com.project.products.springboot_products.services;

import java.util.List;

import com.project.products.springboot_products.entities.Category;

public interface CategoryService {

    Category save(Category category);

    Category findById(Long id);

    List<Category> findAll();

    void deleteById(Long id);

    Category update(Long id, Category category);
} 
