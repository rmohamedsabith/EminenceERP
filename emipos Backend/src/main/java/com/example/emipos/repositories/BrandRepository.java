package com.example.emipos.repositories;

import com.example.emipos.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);

    List<Brand> findAllByActiveTrue();

    Optional<Brand> findByIdAndActiveTrue(Integer id);
}
