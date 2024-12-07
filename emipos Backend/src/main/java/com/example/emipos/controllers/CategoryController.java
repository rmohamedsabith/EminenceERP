package com.example.emipos.controllers;

import com.example.emipos.dtos.CategoryDTO;
import com.example.emipos.services.CategoryService;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a new Category
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    // Update an existing Category
    @PutMapping("/{name}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable("name") String name,
                                                                   @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(name, categoryDTO);
    }

    // Get a Category by ID
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable("name") String name) {
        return categoryService.getCategoryById(name);
    }

    // Get all Categories
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Search categories by keyword
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> searchCategories(@RequestParam String keyword) {
        return categoryService.getCategoriesByKeyword(keyword);
    }

    // Get all category names
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> getCategoryNames() {
        return categoryService.getCategoryNames();
    }
    // Delete a MainCategory by ID
    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse<Void>> deleteMainCategory(@PathVariable String name) {
        return categoryService.deleteCategory(name);
    }
}
