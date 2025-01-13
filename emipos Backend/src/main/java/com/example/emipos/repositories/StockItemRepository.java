package com.example.emipos.repositories;

import com.example.emipos.models.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Integer> {



    Optional<StockItem> findByIdAndActiveTrue(Integer id);

    List<StockItem> findAllByActiveTrue();



    List<StockItem> findByItemNameContainingIgnoreCaseAndActiveTrue(String keyword);
}
