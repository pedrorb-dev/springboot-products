package com.project.products.springboot_products.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.products.springboot_products.entities.Category;
import com.project.products.springboot_products.entities.Product;
import com.project.products.springboot_products.exceptions.NoEntityException;
import com.project.products.springboot_products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product findById(Long id) {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream()
            .filter(product -> product.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoEntityException("Producto no encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    @Transactional
    @Override
    public Product update(Long id, Product product) {
        Product productToUpdate = findById(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategory(product.getCategory());
        return productRepository.save(productToUpdate);
    }

    @Transactional
    @Override
    public Product addCategory(Long id, Category category) {
        Product product = findById(id);
        product.setCategory(category);
        return productRepository.save(product);
    }

    
    
}
