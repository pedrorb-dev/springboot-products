package com.project.products.springboot_products.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.products.springboot_products.entities.Category;
import com.project.products.springboot_products.exceptions.NoEntityException;
import com.project.products.springboot_products.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Long id) {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return categories.stream()
            .filter(category -> category.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoEntityException("Categoria no encontrada"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

    @Transactional
    @Override
    public Category update(Long id,Category category) {
        Category categoryToUpdate = findById(id);
        categoryToUpdate.setName(category.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    
    
}
