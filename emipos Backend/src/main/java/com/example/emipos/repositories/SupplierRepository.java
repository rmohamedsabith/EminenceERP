package com.example.emipos.repositories;

import com.example.emipos.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    List<Supplier> findByNameContainingIgnoreCase(String keyword);

    Optional<Supplier> findByName(String name);
}
