package com.example.emipos.services;

import com.example.emipos.dtos.BrandDTO;
import com.example.emipos.models.Brand;
import com.example.emipos.repositories.BrandRepository;
import com.example.emipos.mappers.BrandMapper;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    // Check for duplicate Brand name
    private boolean isBrandNameDuplicate(String name) {
        return brandRepository.existsByName(name);
    }

    // Create a new Brand
    public ResponseEntity<ApiResponse<BrandDTO>> createBrand(BrandDTO brandDTO) {
        if (isBrandNameDuplicate(brandDTO.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Brand name already exists")
            );
        }

        Brand brand = brandMapper.toEntity(brandDTO);
        Brand savedBrand = brandRepository.save(brand);
        BrandDTO savedBrandDTO = brandMapper.toDto(savedBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(HttpStatus.CREATED.value(), "Brand created successfully", savedBrandDTO)
        );
    }

    // Update an existing Brand
    public ResponseEntity<ApiResponse<BrandDTO>> updateBrand(String name, BrandDTO brandDTO) {
        Optional<Brand> existingBrandOptional = brandRepository.findById(name);
        if (!existingBrandOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Brand not found")
            );
        }

        Brand existingBrand = existingBrandOptional.get();
        existingBrand.setName(brandDTO.getName());
        existingBrand.setDescription(brandDTO.getDescription());
        existingBrand.setCreator(brandDTO.getCreator());
        existingBrand.setActive(brandDTO.getActive());

        Brand updatedBrand = brandRepository.save(existingBrand);
        BrandDTO updatedBrandDTO = brandMapper.toDto(updatedBrand);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Brand updated successfully", updatedBrandDTO)
        );
    }

    // Delete a Brand
    public ResponseEntity<ApiResponse<Void>> deleteBrand(String name) {
        Optional<Brand> brandOptional = brandRepository.findById(name);
        if (!brandOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Brand not found")
            );
        }

//        brandRepository.delete(brandOptional.get());
        brandOptional.get().setActive(false);
        brandRepository.save(brandOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.success(HttpStatus.NO_CONTENT.value(), "Brand deleted successfully", null)
        );
    }

    // Get a Brand by Name
    public ResponseEntity<ApiResponse<BrandDTO>> getBrandByName(String name) {
        Brand brand = brandRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        BrandDTO brandDTO = brandMapper.toDto(brand);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Brand found", brandDTO)
        );
    }

    // Get all Brands
    public ResponseEntity<ApiResponse<List<BrandDTO>>> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "No Brands found")
            );
        }
        List<BrandDTO> brandDTOs = brandMapper.toDtoList(brands);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Brands fetched successfully", brandDTOs)
        );
    }

    // Get Brands by Keyword Search (name contains the keyword)
    public ResponseEntity<ApiResponse<List<BrandDTO>>> getBrandsByKeyword(String keyword) {
        List<Brand> brands = brandRepository.findByNameContainingIgnoreCase(keyword);
        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "No Brands found with the given keyword")
            );
        }
        List<BrandDTO> brandDTOs = brandMapper.toDtoList(brands);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Brands fetched by keyword", brandDTOs)
        );
    }

    // Get all Brand names
    public ResponseEntity<ApiResponse<List<String>>> getListOfBrandNames() {
        List<Brand> brands = brandRepository.findAll();
        List<String> brandNames = brands.stream()
                .map(Brand::getName)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Brand names fetched successfully", brandNames)
        );
    }
}
