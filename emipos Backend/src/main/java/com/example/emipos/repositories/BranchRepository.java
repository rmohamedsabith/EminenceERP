package com.example.emipos.repositories;

import com.example.emipos.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {

    // Method to check if a branch name already exists
    boolean existsByName(String name);
}
