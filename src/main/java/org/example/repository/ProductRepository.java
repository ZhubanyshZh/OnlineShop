package org.example.repository;

import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();
    @Query("SELECT DISTINCT c.name FROM Category c")
    List<String> findDistinctCategory();
    @Query("SELECT DISTINCT p.productBrand FROM Product p")
    List<String> findDistinctProductBrand();
    @Query("SELECT DISTINCT p.size FROM Product p")
    List<String> findDistinctSize();
    @Query("SELECT p FROM Product p " +
            "WHERE (:categories IS NULL OR p.category.id IN (SELECT c.id FROM Category c WHERE c.parentId.id IN (SELECT c1.id FROM Category c1 WHERE c1.name IN (:categories))) " +
            "OR p.category.name IN (:categories)) " +
            "AND (:brands IS NULL OR p.productBrand IN (:brands)) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:sizes IS NULL OR p.size IN (:sizes)) ")
    List<Product> findByFilters(List<String> categories, List<String> brands, Long minPrice, Long maxPrice, List<String> sizes);

    @Query("SELECT new org.example.dto.ProductDto(p.id, p.productName, p.productBrand, p.price, p.size, p.quantity, p.photo, c.name, p.discountCreatedAt, p.discountFinishedAt, p.discount) " +
            "FROM Product p INNER JOIN Category c ON c.id = p.category.id")
    List<ProductDto> findAllWithCategoryName();
}