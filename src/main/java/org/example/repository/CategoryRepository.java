package org.example.repository;

import org.example.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findCategoryByName(String name);

    @Query("SELECT c.name FROM Category c WHERE c.parentId.id IS NOT NULL")
    List<String> findCategories();

    @Query("SELECT c.name FROM Category c")
    List<String> findAllCategory();
}
