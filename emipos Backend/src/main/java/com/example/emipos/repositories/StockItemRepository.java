package com.example.emipos.repositories;

import com.example.emipos.models.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Integer> {


    List<StockItem> findByItemNameContainingIgnoreCase(String keyword);

    Optional<StockItem> findByItemName(String name);
}
