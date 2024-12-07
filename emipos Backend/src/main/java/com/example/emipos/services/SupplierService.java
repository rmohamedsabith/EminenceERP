package com.example.emipos.services;

import com.example.emipos.dtos.SupplierDTO;
import com.example.emipos.models.Supplier;
import com.example.emipos.models.Supplier;
import com.example.emipos.repositories.SupplierRepository;
import com.example.emipos.mappers.SupplierMapper;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    // Create a new Supplier
    public ResponseEntity<ApiResponse<SupplierDTO>> createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        SupplierDTO savedSupplierDTO = supplierMapper.toDto(savedSupplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(HttpStatus.CREATED.value(), "Supplier created successfully", savedSupplierDTO)
        );
    }

    // Update an existing Supplier
    public ResponseEntity<ApiResponse<SupplierDTO>> updateSupplier(Integer id, SupplierDTO supplierDTO) {
        Optional<Supplier> existingSupplierOptional = supplierRepository.findById(id);
        if (existingSupplierOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Supplier not found")
            );
        }

        Supplier existingSupplier = existingSupplierOptional.get();
        // Update the fields
        existingSupplier.setName(supplierDTO.getName());
        existingSupplier.setDescription(supplierDTO.getDescription());
        existingSupplier.setCompanyName(supplierDTO.getCompanyName());
        existingSupplier.setContactPersonName(supplierDTO.getContactPersonName());
        existingSupplier.setContactNumber(supplierDTO.getContactNumber());
        existingSupplier.setBank(supplierDTO.getBank());
        existingSupplier.setBranch(supplierDTO.getBranch());
        existingSupplier.setAccountNumber(supplierDTO.getAccountNumber());
        existingSupplier.setAddress(supplierDTO.getAddress());
        existingSupplier.setFax(supplierDTO.getFax());
        existingSupplier.setEmail(supplierDTO.getEmail());
        existingSupplier.setWeb(supplierDTO.getWeb());
        existingSupplier.setReason(supplierDTO.getReason());

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        SupplierDTO updatedSupplierDTO = supplierMapper.toDto(updatedSupplier);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Supplier updated successfully", updatedSupplierDTO)
        );
    }

    // Delete a Supplier (soft delete)
    public ResponseEntity<ApiResponse<Void>> deleteSupplier(Integer id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Supplier not found")
            );
        }

        Supplier supplierToDelete = supplierOptional.get();
        supplierToDelete.setActive(false);
        supplierRepository.save(supplierToDelete);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.success(HttpStatus.NO_CONTENT.value(), "Supplier deleted successfully", null)
        );
    }

    // Get a Supplier by Name
    public ResponseEntity<ApiResponse<SupplierDTO>> getSupplierByName(String name) {
        Optional<Supplier> supplierOptional = supplierRepository.findByName(name);
        if (supplierOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Supplier not found")
            );
        }

        Supplier supplier = supplierOptional.get();
        SupplierDTO supplierDTO = supplierMapper.toDto(supplier);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Supplier found", supplierDTO)
        );
    }

    // Get all Suppliers
    public ResponseEntity<ApiResponse<List<SupplierDTO>>> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDTO> supplierDTOs = supplierMapper.toDtoList(suppliers);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Suppliers fetched successfully", supplierDTOs)
        );
    }
    // Get Suppliers by Keyword Search (name contains the keyword)
    public ResponseEntity<ApiResponse<List<SupplierDTO>>> getSuppliersByKeyword(String keyword) {
        List<Supplier> suppliers = supplierRepository.findByNameContainingIgnoreCase(keyword);
        if (suppliers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "No Suppliers found with the given keyword")
            );
        }
        List<SupplierDTO> supplierDTO = supplierMapper.toDtoList(suppliers);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Suppliers fetched by keyword", supplierDTO)
        );
    }

    // Get all Supplier names
    public ResponseEntity<ApiResponse<List<String>>> getListOfSupplierNames() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<String> supplierName = suppliers.stream()
                .map(Supplier::getName)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Supplier names fetched successfully", supplierName)
        );
    }
}
