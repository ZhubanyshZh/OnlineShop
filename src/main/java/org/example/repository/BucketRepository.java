package org.example.repository;

import org.example.dto.BucketDto;
import org.example.entity.Bucket;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bucket b WHERE b.productId = :productId AND b.userId = :userId")
    boolean haveTheProductThisUser(Long productId, Long userId);

    @Query("SELECT new org.example.dto.BucketDto(b.id, :userId, p, b.amount) FROM Product p INNER JOIN Bucket b ON p.id = b.productId WHERE b.userId = :userId")
    ArrayList<BucketDto> findProductsByUserId(Long userId);

    Bucket findBucketByUserIdAndProductId(Long userId, Long productId);
}
