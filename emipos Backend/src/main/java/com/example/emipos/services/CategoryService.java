package com.example.emipos.services;

import com.example.emipos.dtos.CategoryDTO;
import com.example.emipos.models.Category;
import com.example.emipos.models.Category;
import com.example.emipos.repositories.CategoryRepository;
import com.example.emipos.mappers.CategoryMapper;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Autowired
    private AuditorAware<String> auditorAware;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    // Check for duplicate category by name
    private boolean isCategoryNameDuplicate(String name) {
        return categoryRepository.existsByName(name);
    }

    @Transactional
    // Create a new Category
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(CategoryDTO categoryDTO) {
        Optional<String> auditor = auditorAware.getCurrentAuditor();
        System.out.println("Current auditor: " + auditor.orElse("None"));

        if (isCategoryNameDuplicate(categoryDTO.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Category name already exists")
            );
        }

        //Category category = categoryMapper.toEntity(categoryDTO);
        Category category=new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        Category savedCategory = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = categoryMapper.toDto(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(HttpStatus.CREATED.value(), "Category created successfully", savedCategoryDTO)
        );
    }

    // Update an existing Category
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(String id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Update fields
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        existingCategory.setCreator(categoryDTO.getCreator());
        existingCategory.setActive(categoryDTO.getActive());

        Category updatedCategory = categoryRepository.save(existingCategory);
        CategoryDTO updatedCategoryDTO = categoryMapper.toDto(updatedCategory);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Category updated successfully", updatedCategoryDTO)
        );
    }

    // Get a Category by ID
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Category not found")
            );
        }
        CategoryDTO categoryDTO = categoryMapper.toDto(categoryOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Category found", categoryDTO)
        );
    }

    // Get all Categories
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOs = categoryMapper.toDtoList(categories);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Categories fetched successfully", categoryDTOs)
        );
    }

    // Get categories by keyword
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getCategoriesByKeyword(String keyword) {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(keyword);
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "No categories found with the given keyword")
            );
        }
        List<CategoryDTO> categoryDTOs = categoryMapper.toDtoList(categories);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Categories fetched by keyword", categoryDTOs)
        );
    }

    // Get list of Category names
    public ResponseEntity<ApiResponse<List<String>>> getCategoryNames() {
        List<Category> categories = categoryRepository.findAll();
        List<String> categoryNames = categories.stream()
                .map(Category::getName)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Category names fetched successfully", categoryNames)
        );
    }
    // Delete a Category
    public ResponseEntity<ApiResponse<Void>> deleteCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Category not found")
            );
        }

//        categoryRepository.delete(categoryOptional.get());
        categoryOptional.get().setActive(false);
        categoryRepository.save(categoryOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.success(HttpStatus.NO_CONTENT.value(), "Category deleted successfully", null)
        );
    }
}
