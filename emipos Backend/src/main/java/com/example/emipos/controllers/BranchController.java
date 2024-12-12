package com.example.emipos.controllers;

import com.example.emipos.dtos.BranchDTO;
import com.example.emipos.services.BranchService;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // Create a new Branch
    @PostMapping
    public ResponseEntity<ApiResponse<BranchDTO>> createBranch(@RequestBody BranchDTO branchDTO) {
        return branchService.createBranch(branchDTO);
    }

    // Update an existing Branch
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchDTO>> updateBranch(@PathVariable Integer id, @RequestBody BranchDTO branchDTO) {
        return branchService.updateBranch(id, branchDTO);
    }

    // Delete a Branch
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBranch(@PathVariable Integer id) {
        return branchService.deleteBranch(id);
    }

    // Get a Branch by Name
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchDTO>> getBranchById(@PathVariable Integer id) {
        return branchService.getBranchById(id);
    }

    // Get all Branches
    @GetMapping
    public ResponseEntity<ApiResponse<List<BranchDTO>>> getAllBranches() {
        return branchService.getAllBranches();
    }

    // Get all Branch ids
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> getListOfBranchNames() {
        return branchService.getListOfBranchNames();
    }
}
