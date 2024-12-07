package com.example.emipos.repositories;

import com.example.emipos.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {


    // Custom query to get all MainCategories that contain a keyword in the name or description
    List<Category> findByNameContainingIgnoreCase(String nameKeyword);

    boolean existsByName(String name);
}
