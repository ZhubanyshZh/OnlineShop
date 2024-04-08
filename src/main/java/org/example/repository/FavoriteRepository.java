package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.Favorite;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Favorite f WHERE f.productId = :productId AND f.userId = :userId")
    boolean isTheProductFavorite(Long productId, Long userId);
    @Query("SELECT p FROM Favorite f JOIN Product p ON p.id = f.productId WHERE f.userId = :userId")
    ArrayList<Product> getAllProductsFromFavorite(Long userId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.userId = :userId AND f.productId = :productId")
    void delete(Long productId, Long userId);
}
