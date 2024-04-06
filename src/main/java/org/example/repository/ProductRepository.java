package org.example.repository;

import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategory();
    @Query("SELECT DISTINCT p.productBrand FROM Product p")
    List<String> findDistinctProductBrand();
    @Query("SELECT DISTINCT p.size FROM Product p")
    List<String> findDistinctSize();
    @Query("SELECT p FROM Product p " +
            "WHERE (:categories IS NULL OR p.category IN (:categories)) " +
            "AND (:brands IS NULL OR p.productBrand IN (:brands)) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:sizes IS NULL OR p.size IN (:sizes))")
    List<Product> findByFilters(List<String> categories, List<String> brands, Long minPrice, Long maxPrice, List<String> sizes);


}
