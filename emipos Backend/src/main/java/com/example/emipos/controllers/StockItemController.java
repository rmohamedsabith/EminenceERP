package com.example.emipos.controllers;

import com.example.emipos.dtos.StockItemDTO;
import com.example.emipos.responses.ApiResponse;
import com.example.emipos.services.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-items")
public class StockItemController {

    private final StockItemService stockItemService;

    @Autowired
    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    // Create a new StockItem
    @PostMapping
    public ResponseEntity<ApiResponse<StockItemDTO>> createStockItem(@RequestBody StockItemDTO stockItemDTO) {
        return stockItemService.createStockItem(stockItemDTO);
    }

    // Update an existing StockItem
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StockItemDTO>> updateStockItem(@PathVariable Integer id, @RequestBody StockItemDTO stockItemDTO) {
        return stockItemService.updateStockItem(id, stockItemDTO);
    }

    // Delete a StockItem
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStockItem(@PathVariable Integer id) {
        return stockItemService.deleteStockItem(id);
    }

    // Get a StockItem by Name
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StockItemDTO>> getStockItemById(@PathVariable Integer id) {
        return stockItemService.getStockItemById(id);
    }

    // Get all StockItems
    @GetMapping
    public ResponseEntity<ApiResponse<List<StockItemDTO>>> getAllStockItems() {
        return stockItemService.getAllStockItems();
    }

    // Get StockItems by Keyword Search (name contains the keyword)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StockItemDTO>>> getStockItemsByKeyword(@RequestParam String keyword) {
        return stockItemService.getStockItemsByKeyword(keyword);
    }

    // Get all StockItem names
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> getListOfStockItemNames() {
        return stockItemService.getListOfStockItemNames();
    }
}
