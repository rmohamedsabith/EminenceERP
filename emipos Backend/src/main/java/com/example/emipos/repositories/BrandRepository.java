package com.example.emipos.repositories;

import com.example.emipos.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    List<Brand> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);
}
