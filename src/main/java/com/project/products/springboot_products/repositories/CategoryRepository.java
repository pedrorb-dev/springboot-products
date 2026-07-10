package com.project.products.springboot_products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.products.springboot_products.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
