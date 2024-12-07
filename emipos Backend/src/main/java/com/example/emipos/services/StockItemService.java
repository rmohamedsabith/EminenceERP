package com.example.emipos.services;

import com.example.emipos.dtos.StockItemDTO;
import com.example.emipos.models.StockItem;
import com.example.emipos.repositories.StockItemRepository;
import com.example.emipos.mappers.StockItemMapper;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockItemService {

    private final StockItemRepository stockItemRepository;
    private final StockItemMapper stockItemMapper;

    @Autowired
    public StockItemService(StockItemRepository stockItemRepository, StockItemMapper stockItemMapper) {
        this.stockItemRepository = stockItemRepository;
        this.stockItemMapper = stockItemMapper;
    }

    // Create a new StockItem
    public ResponseEntity<ApiResponse<StockItemDTO>> createStockItem(StockItemDTO stockItemDTO) {
        StockItem stockItem = stockItemMapper.toEntity(stockItemDTO);
        StockItem savedStockItem = stockItemRepository.save(stockItem);
        StockItemDTO savedStockItemDTO = stockItemMapper.toDto(savedStockItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(HttpStatus.CREATED.value(), "StockItem created successfully", savedStockItemDTO)
        );
    }

    // Update an existing StockItem
    public ResponseEntity<ApiResponse<StockItemDTO>> updateStockItem(Integer id, StockItemDTO stockItemDTO) {
        Optional<StockItem> existingStockItemOptional = stockItemRepository.findById(id);
        if (existingStockItemOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "StockItem not found")
            );
        }

        StockItem existingStockItem = existingStockItemOptional.get();
        // Update the fields
        existingStockItem.setItemCode(stockItemDTO.getItemCode());
        existingStockItem.setItemName(stockItemDTO.getItemName());
        existingStockItem.setItemDescription(stockItemDTO.getItemDescription());
        existingStockItem.setQuantity(stockItemDTO.getQuantity());
        // Add other fields as needed

        StockItem updatedStockItem = stockItemRepository.save(existingStockItem);
        StockItemDTO updatedStockItemDTO = stockItemMapper.toDto(updatedStockItem);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "StockItem updated successfully", updatedStockItemDTO)
        );
    }

    // Delete a StockItem
    public ResponseEntity<ApiResponse<Void>> deleteStockItem(Integer id) {
        Optional<StockItem> stockItemOptional = stockItemRepository.findById(id);
        if (stockItemOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "StockItem not found")
            );
        }

//        stockItemRepository.delete(stockItemOptional.get());
        stockItemOptional.get().setActive(false);
        stockItemRepository.save(stockItemOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.success(HttpStatus.NO_CONTENT.value(), "StockItem deleted successfully", null)
        );
    }

    // Get a StockItem by ID
    public ResponseEntity<ApiResponse<StockItemDTO>> getStockItemByName(String name) {
        // Find the StockItem by name
        Optional<StockItem> stockItemOptional = stockItemRepository.findByItemName(name);

        // Check if StockItem exists
        if (stockItemOptional.isEmpty()) {
            // If not found, return a 404 response with an error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "StockItem not found")
            );
        }

        // Convert the StockItem entity to DTO
        StockItem stockItem = stockItemOptional.get();
        StockItemDTO stockItemDTO = stockItemMapper.toDto(stockItem);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "StockItem found", stockItemDTO)
        );
    }

    // Get all StockItems
    public ResponseEntity<ApiResponse<List<StockItemDTO>>> getAllStockItems() {
        List<StockItem> stockItems = stockItemRepository.findAll();
        List<StockItemDTO> stockItemDTOs = stockItemMapper.toDtoList(stockItems);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "StockItems fetched successfully", stockItemDTOs)
        );
    }

    // Get StockItems by Keyword Search (name contains the keyword)
    public ResponseEntity<ApiResponse<List<StockItemDTO>>> getStockItemsByKeyword(String keyword) {
        List<StockItem> stocks = stockItemRepository.findByItemNameContainingIgnoreCase(keyword);
        if (stocks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "No StockItems found with the given keyword")
            );
        }
        List<StockItemDTO> brandDTOs = stockItemMapper.toDtoList(stocks);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "StockItems fetched by keyword", brandDTOs)
        );
    }

    // Get all StockItem names
    public ResponseEntity<ApiResponse<List<String>>> getListOfStockItemNames() {
        List<StockItem> stocks = stockItemRepository.findAll();
        List<String> stockNames = stocks.stream()
                .map(StockItem::getItemName)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "StockItem names fetched successfully", stockNames)
        );
    }
}
