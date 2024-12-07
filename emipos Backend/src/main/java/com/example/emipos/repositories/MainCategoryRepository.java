package com.example.emipos.repositories;

import com.example.emipos.models.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, String> {

    // Custom query to find MainCategory by name
    Optional<MainCategory> findByName(String name);

    // Custom query to get all MainCategories that contain a keyword in the name or description
    List<MainCategory> findByNameContainingIgnoreCase(String nameKeyword);

    boolean existsByName(String name);
}
