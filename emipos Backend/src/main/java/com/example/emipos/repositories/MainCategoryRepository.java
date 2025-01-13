package com.example.emipos.repositories;

import com.example.emipos.models.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {



    boolean existsByName(String name);

    List<MainCategory> findAllByActiveTrue();

    Optional<MainCategory> findByIdAndActiveTrue(Integer id);



    List<MainCategory> findByNameContainingIgnoreCaseAndActiveTrue(String keyword);
}
