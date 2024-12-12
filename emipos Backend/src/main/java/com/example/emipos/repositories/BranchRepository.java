package com.example.emipos.repositories;

import com.example.emipos.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch,Integer > {

    // Method to check if a branch name already exists
    boolean existsByName(String name);

    List<Branch> findAllByActiveTrue();

    Optional<Branch> findByIdAndActiveTrue(Integer id);
}
