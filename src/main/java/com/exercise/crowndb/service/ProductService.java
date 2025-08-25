package com.exercise.crowndb.service;


import com.exercise.crowndb.model.Category;
import com.exercise.crowndb.model.Product;
import com.exercise.crowndb.repository.CategoryRepository;
import com.exercise.crowndb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<Product> getProductByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    public Page<Product> getAllProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        if (product.getCategoryID() == null || product.getCategoryID().getId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null.");
        }

        Category category = categoryRepository.findById(product.getCategoryID().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Category ID provided."));
        product.setCategoryID(category);
        return productRepository.save(product);
    }

    public Product createOrRetrieveProductByBarcode(Product newProduct) {
        if (newProduct.getBarcode() == null || newProduct.getBarcode().trim().isEmpty()) {
            throw new IllegalArgumentException("Barcode cannot be null or empty for createOrRetrieveProductByBarcode.");
        }

        Optional<Product> existingProduct = productRepository.findByBarcode(newProduct.getBarcode());

        if (existingProduct.isPresent()) {
            return existingProduct.get();
        } else {
            if (newProduct.getCategoryID() == null || newProduct.getCategoryID().getId() == null) {
                throw new IllegalArgumentException("Category ID cannot be null for a new product.");
            }
            Category category = categoryRepository.findById(newProduct.getCategoryID().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Category ID for new product."));

            newProduct.setCategoryID(category);

            return productRepository.save(newProduct);
        }
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setShortName(updatedProduct.getShortName());
            existingProduct.setBarcode(updatedProduct.getBarcode());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setAllowCityTax(updatedProduct.getAllowCityTax());
            existingProduct.setCustomCode(updatedProduct.getCustomCode());
            existingProduct.setImagePath(updatedProduct.getImagePath());
            existingProduct.setPackageCount(updatedProduct.getPackageCount());
            existingProduct.setMainCategoryCode(updatedProduct.getMainCategoryCode());
            existingProduct.setIsVATFree(updatedProduct.getIsVATFree());

            if (updatedProduct.getCategoryID() != null && updatedProduct.getCategoryID().getId() != null) {
                Category newCategory = categoryRepository.findById(updatedProduct.getCategoryID().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Category ID for update."));
                existingProduct.setCategoryID(newCategory);
            }

            return Optional.of(productRepository.save(existingProduct));
        } else {
            return Optional.empty();
        }
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchByNameContainingIgnoreCase(String name) {
        return productRepository.searchByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategory(Long categoryID) {
        return productRepository.findByCategoryID_Id(categoryID);
    }

    public Page<Product> getProductsByCategoryPaginated(Long categoryID, int page, int size) {
        // Create a Pageable object for the query
        Pageable pageable = PageRequest.of(page, size);
        // Use the product repository to find products by category ID with pagination
        return productRepository.findByCategoryID_Id(categoryID, pageable);
    }
}