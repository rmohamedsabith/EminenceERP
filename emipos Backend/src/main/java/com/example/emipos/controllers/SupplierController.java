package com.example.emipos.controllers;

import com.example.emipos.dtos.SupplierDTO;
import com.example.emipos.responses.ApiResponse;
import com.example.emipos.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Create a new Supplier
    @PostMapping
    public ResponseEntity<ApiResponse<SupplierDTO>> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

    // Update an existing Supplier
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierDTO>> updateSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        return supplierService.updateSupplier(id, supplierDTO);
    }

    // Delete a Supplier (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }

    // Get a Supplier by Name
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierDTO>> getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    // Get all Suppliers
    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplierDTO>>> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Get Suppliers by Keyword Search (name contains the keyword)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SupplierDTO>>> getSuppliersByKeyword(@RequestParam String keyword) {
        return supplierService.getSuppliersByKeyword(keyword);
    }

    // Get all Supplier names
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> getListOfSupplierNames() {
        return supplierService.getListOfSupplierNames();
    }
}
