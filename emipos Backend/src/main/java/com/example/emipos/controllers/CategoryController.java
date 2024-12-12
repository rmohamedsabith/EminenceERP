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
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable("id") Integer id,
                                                                   @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(id, categoryDTO);
    }

    // Get a Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable("id") Integer id) {
        return categoryService.getCategoryById(id);
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

    // Get all category ids
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> getCategoryNames() {
        return categoryService.getCategoryNames();
    }
    // Delete a MainCategory by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMainCategory(@PathVariable Integer id) {
        return categoryService.deleteCategory(id);
    }
}
