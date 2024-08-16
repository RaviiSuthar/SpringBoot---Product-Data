package com.example.Product.ProductData.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Product.ProductData.Entities.Category;
import com.example.Product.ProductData.Entities.Product;
import com.example.Product.ProductData.Repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(int page) {
        //im keeping page size as 5
        Page<Category> allCategories = categoryRepository.findAll(PageRequest.of(page,5));

        for(Category category : allCategories.getContent()){
            for(Product product:  category.getProducts()){
                product.setCategory(null);
            }
        }
        return allCategories;

    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .map(category -> {
                for(Product product : category.getProducts()){
                    product.setCategory(null);
                }

                return category;
            })
            .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        return categoryRepository.findById(id)
            .map(category -> {
                category.setName(categoryDetails.getName());
                categoryRepository.save(category);

                for(Product product : category.getProducts()){
                    product.setCategory(null);
                }

                return category;
            })
            .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    public Category deleteCategory(Long id) {
       return categoryRepository.findById(id)
            .map(category -> {
                categoryRepository.delete(category);
                for(Product product : category.getProducts()){
                    product.setCategory(null);
                }

                return category;
            })
            .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }
}
