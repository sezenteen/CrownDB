package com.exercise.crowndb.service;


import com.exercise.crowndb.model.Category;
import com.exercise.crowndb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Corrected updateCategory method
    public Optional<Category> updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(id);

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            // Update the fields you want to allow updating
            existingCategory.setName(updatedCategory.getName());
            // You might have other fields to update here

            return Optional.of(categoryRepository.save(existingCategory)); // Save and return the updated category
        } else {
            return Optional.empty(); // Category not found
        }
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
}