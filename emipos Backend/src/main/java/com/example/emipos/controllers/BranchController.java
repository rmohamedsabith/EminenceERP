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
    @PutMapping("/{name}")
    public ResponseEntity<ApiResponse<BranchDTO>> updateBranch(@PathVariable String name, @RequestBody BranchDTO branchDTO) {
        return branchService.updateBranch(name, branchDTO);
    }

    // Delete a Branch
    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse<Void>> deleteBranch(@PathVariable String name) {
        return branchService.deleteBranch(name);
    }

    // Get a Branch by Name
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse<BranchDTO>> getBranchByName(@PathVariable String name) {
        return branchService.getBranchByName(name);
    }

    // Get all Branches
    @GetMapping
    public ResponseEntity<ApiResponse<List<BranchDTO>>> getAllBranches() {
        return branchService.getAllBranches();
    }

    // Get all Branch names
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> getListOfBranchNames() {
        return branchService.getListOfBranchNames();
    }
}
