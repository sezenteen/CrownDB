package com.exercise.crowndb.controller;

import com.exercise.crowndb.model.Category;
import com.exercise.crowndb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok) // If present, return 200 OK with category
                .orElseGet(() -> ResponseEntity.notFound().build()); // Else, return 404 Not Found
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Return 201 Created for successful POST
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> updatedCategory = categoryService.updateCategory(id, category); // CORRECTED CALL
        return updatedCategory.map(ResponseEntity::ok) // If present, return 200 OK with updated category
                .orElseGet(() -> ResponseEntity.notFound().build()); // Else, return 404 Not Found
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Return 204 No Content for successful DELETE
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
