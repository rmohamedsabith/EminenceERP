package com.example.emipos.controllers;

import com.example.emipos.dtos.MainCategoryDTO;
import com.example.emipos.responses.ApiResponse;
import com.example.emipos.services.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maincategories")
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;

    @Autowired
    public MainCategoryController(MainCategoryService mainCategoryService) {
        this.mainCategoryService = mainCategoryService;
    }

    // Create a new MainCategory
    @PostMapping
    public ResponseEntity<ApiResponse<MainCategoryDTO>> createMainCategory(@RequestBody MainCategoryDTO mainCategoryDTO) {
        return mainCategoryService.createMainCategory(mainCategoryDTO);
    }

    // Update an existing MainCategory by ID
    @PutMapping("/{name}")
    public ResponseEntity<ApiResponse<MainCategoryDTO>> updateMainCategory(@PathVariable String name, @RequestBody MainCategoryDTO mainCategoryDTO) {
        return mainCategoryService.updateMainCategory(name, mainCategoryDTO);
    }


    // Get a MainCategory by its Name
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse<MainCategoryDTO>> getMainCategoryByName(@PathVariable String name) {
        return mainCategoryService.getMainCategoryByName(name);
    }

    // Get all MainCategories
    @GetMapping
    public ResponseEntity<ApiResponse<List<MainCategoryDTO>>> getAllMainCategories() {
        return mainCategoryService.getAllMainCategories();
    }

    // Get all MainCategories by a keyword search
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<MainCategoryDTO>>> getMainCategoriesByKeyword(@RequestParam String keyword) {
        return mainCategoryService.getMainCategoriesByKeyword(keyword);
    }

    // Get all MainCategory name
    @GetMapping("/name")
    public ResponseEntity<ApiResponse<List<String>>> getListOfNames() {
        return mainCategoryService.getListOfNames();
    }

    // Delete a MainCategory by ID
    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse<Void>> deleteMainCategory(@PathVariable String name) {
        return mainCategoryService.deleteMainCategory(name);
    }
}
