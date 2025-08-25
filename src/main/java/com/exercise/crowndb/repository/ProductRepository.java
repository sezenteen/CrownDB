package com.exercise.crowndb.repository;

import com.exercise.crowndb.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByBarcode(String barcode);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByNameContainingIgnoreCase(String name);

    // Existing method to get all products by category ID
    List<Product> findByCategoryID_Id(Long categoryId);

    Page<Product> findByCategoryID_Id(Long categoryId, Pageable pageable);
}