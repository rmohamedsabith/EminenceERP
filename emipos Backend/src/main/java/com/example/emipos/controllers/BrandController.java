package com.example.emipos.controllers;

import com.example.emipos.dtos.BrandDTO;
import com.example.emipos.services.BrandService;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    // Create a new Brand
    @PostMapping
    public ResponseEntity<ApiResponse<BrandDTO>> createBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }

    // Update an existing Brand
    @PutMapping("/{name}")
    public ResponseEntity<ApiResponse<BrandDTO>> updateBrand(@PathVariable String name, @RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(name, brandDTO);
    }

    // Delete a Brand
    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse<Void>> deleteBrand(@PathVariable String name) {
        return brandService.deleteBrand(name);
    }

    // Get a Brand by Name
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse<BrandDTO>> getBrandByName(@PathVariable String name) {
        return brandService.getBrandByName(name);
    }

    // Get all Brands
    @GetMapping
    public ResponseEntity<ApiResponse<List<BrandDTO>>> getAllBrands() {
        return brandService.getAllBrands();
    }

    // Get Brands by Keyword Search (name contains the keyword)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<BrandDTO>>> getBrandsByKeyword(@RequestParam String keyword) {
        return brandService.getBrandsByKeyword(keyword);
    }

    // Get all Brand names
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> getListOfBrandNames() {
        return brandService.getListOfBrandNames();
    }
}
