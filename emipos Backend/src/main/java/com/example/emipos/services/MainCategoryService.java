package com.example.emipos.services;

import com.example.emipos.dtos.MainCategoryDTO;
import com.example.emipos.models.MainCategory;
import com.example.emipos.repositories.MainCategoryRepository;
import com.example.emipos.mappers.MainCategoryMapper;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final MainCategoryMapper mainCategoryMapper;

    @Autowired
    public MainCategoryService(MainCategoryRepository mainCategoryRepository, MainCategoryMapper mainCategoryMapper) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.mainCategoryMapper = mainCategoryMapper;
    }

    // Check for duplicate MainCategory name
    private boolean isMainCategoryNameDuplicate(String name) {
        return mainCategoryRepository.existsByName(name);
    }

    // Create a new MainCategory
    public ResponseEntity<ApiResponse<MainCategoryDTO>> createMainCategory(MainCategoryDTO mainCategoryDTO) {
        if (isMainCategoryNameDuplicate(mainCategoryDTO.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "MainCategory name already exists")
            );
        }

        MainCategory mainCategory = mainCategoryMapper.toEntity(mainCategoryDTO);
        MainCategory savedMainCategory = mainCategoryRepository.save(mainCategory);
        MainCategoryDTO savedMainCategoryDTO = mainCategoryMapper.toDto(savedMainCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(HttpStatus.CREATED.value(), "MainCategory created successfully", savedMainCategoryDTO)
        );
    }

    // Update an existing MainCategory
    public ResponseEntity<ApiResponse<MainCategoryDTO>> updateMainCategory(String id, MainCategoryDTO mainCategoryDTO) {
        Optional<MainCategory> existingMainCategoryOptional = mainCategoryRepository.findById(id);
        if (!existingMainCategoryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "MainCategory not found")
            );
        }

        MainCategory existingMainCategory = existingMainCategoryOptional.get();
        existingMainCategory.setName(mainCategoryDTO.getName());
        existingMainCategory.setDescription(mainCategoryDTO.getDescription());
        existingMainCategory.setActive(mainCategoryDTO.getActive());

        MainCategory updatedMainCategory = mainCategoryRepository.save(existingMainCategory);
        MainCategoryDTO updatedMainCategoryDTO = mainCategoryMapper.toDto(updatedMainCategory);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "MainCategory updated successfully", updatedMainCategoryDTO)
        );
    }

    // Delete a MainCategory
    public ResponseEntity<ApiResponse<Void>> deleteMainCategory(String id) {
        Optional<MainCategory> mainCategoryOptional = mainCategoryRepository.findById(id);
        if (!mainCategoryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "MainCategory not found")
            );
        }

//        mainCategoryRepository.delete(mainCategoryOptional.get());
        mainCategoryOptional.get().setActive(false);
        mainCategoryRepository.save(mainCategoryOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.success(HttpStatus.NO_CONTENT.value(), "MainCategory deleted successfully", null)
        );
    }

    // Get a MainCategory by Name
    public ResponseEntity<ApiResponse<MainCategoryDTO>> getMainCategoryByName(String name) {
        MainCategory mainCategory = mainCategoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("MainCategory not found"));
        MainCategoryDTO mainCategoryDTO = mainCategoryMapper.toDto(mainCategory);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "MainCategory found", mainCategoryDTO)
        );
    }

    // Get all MainCategories
    public ResponseEntity<ApiResponse<List<MainCategoryDTO>>> getAllMainCategories() {
        List<MainCategory> mainCategories = mainCategoryRepository.findAll();
        if (mainCategories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "No MainCategories found")
            );
        }
        List<MainCategoryDTO> mainCategoryDTOs = mainCategoryMapper.toDtoList(mainCategories);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "MainCategories fetched successfully", mainCategoryDTOs)
        );
    }

    // Get MainCategories by Keyword Search (name contains the keyword)
    public ResponseEntity<ApiResponse<List<MainCategoryDTO>>> getMainCategoriesByKeyword(String keyword) {
        List<MainCategory> mainCategories = mainCategoryRepository.findByNameContainingIgnoreCase(keyword);
        if (mainCategories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "No MainCategories found with the given keyword")
            );
        }
        List<MainCategoryDTO> mainCategoryDTOs = mainCategoryMapper.toDtoList(mainCategories);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "MainCategories fetched by keyword", mainCategoryDTOs)
        );
    }

    // Get all MainCategory names
    public ResponseEntity<ApiResponse<List<String>>> getListOfNames() {
        List<MainCategory> mainCategories = mainCategoryRepository.findAll();
        List<String> categoryNames = mainCategories.stream()
                .map(MainCategory::getName)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "MainCategory names fetched successfully", categoryNames)
        );
    }
}
