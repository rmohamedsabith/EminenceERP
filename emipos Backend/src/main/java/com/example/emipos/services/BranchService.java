package com.example.emipos.services;

import com.example.emipos.dtos.BranchDTO;
import com.example.emipos.models.Branch;
import com.example.emipos.repositories.BranchRepository;
import com.example.emipos.mappers.BranchMapper;
import com.example.emipos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Autowired
    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    // Check for duplicate Branch name
    private boolean isBranchNameDuplicate(String name) {
        return branchRepository.existsByName(name);
    }

    // Ensure only one Branch is marked as 'isMain'
    private void makeAllBranchIsMainFalse() {
        // Get all branches from the repository
        List<Branch> allBranches = branchRepository.findAllByActiveTrue();

        // Mark all branches as 'isMain = false' first
        allBranches.forEach(branch -> {
            branch.setIsMain(false);
            branchRepository.save(branch);
        });
    }


    // Create a new Branch
    public ResponseEntity<ApiResponse<BranchDTO>> createBranch(BranchDTO branchDTO) {
        // Check if the branch name already exists
        if (isBranchNameDuplicate(branchDTO.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Branch name already exists")
            );
        }

        // Convert DTO to entity
        Branch branch = branchMapper.toEntity(branchDTO);

        // Ensure only one branch has isMain = true
        if (branchRepository.count() == 0) {
            // If no branches exist, set the first one as 'isMain = true'
            branch.setIsMain(true);
        } else if(branchDTO.getIsMain() != null && branchDTO.getIsMain()) {
           makeAllBranchIsMainFalse();
        }

        // Save the new branch to the database
        Branch savedBranch = branchRepository.save(branch);
        BranchDTO savedBranchDTO = branchMapper.toDto(savedBranch);

        // Return success response
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(HttpStatus.CREATED.value(), "Branch created successfully", savedBranchDTO)
        );
    }

    // Update an existing Branch
    public ResponseEntity<ApiResponse<BranchDTO>> updateBranch(Integer id, BranchDTO branchDTO) {
        // Find the existing branch by id
        Optional<Branch> existingBranchOptional = branchRepository.findByIdAndActiveTrue(id);
        if (existingBranchOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Branch not found")
            );
        }

        // Get the existing branch from the Optional
        Branch existingBranch = existingBranchOptional.get();

        // If 'isMain' is set to true, ensure only one branch has isMain = true
        if (branchDTO.getIsMain() != null && branchDTO.getIsMain()) {
            // If isMain is true in the DTO, set all other branches' isMain to false
            makeAllBranchIsMainFalse();
        }

        // Update the branch details
        existingBranch.setName(branchDTO.getName());
        existingBranch.setDescription(branchDTO.getDescription());
        existingBranch.setIsMain(branchDTO.getIsMain() != null ? branchDTO.getIsMain() : existingBranch.getIsMain());
        existingBranch.setCreator(branchDTO.getCreator());
        existingBranch.setActive(branchDTO.getActive());

        // Save the updated branch to the database
        Branch updatedBranch = branchRepository.save(existingBranch);
        BranchDTO updatedBranchDTO = branchMapper.toDto(updatedBranch);

        // Return success response
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Branch updated successfully", updatedBranchDTO)
        );
    }



    // Delete a Branch
    public ResponseEntity<ApiResponse<Void>> deleteBranch(Integer id) {
        Optional<Branch> branchOptional = branchRepository.findByIdAndActiveTrue(id);
        if (branchOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Branch not found")
            );
        }

        Branch branchToDelete = branchOptional.get();

        // Check if the branch to delete is the main branch
        if (branchToDelete.getIsMain()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Main branch cannot be deleted")
            );
        }

        branchToDelete.setActive(false);
//        branchRepository.delete(branchToDelete);
        branchRepository.save(branchToDelete);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.success(HttpStatus.NO_CONTENT.value(), "Branch deleted successfully", null)
        );
    }

    // Get a Branch by Name
    public ResponseEntity<ApiResponse<BranchDTO>> getBranchById(Integer id) {
        Branch branch = branchRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        BranchDTO branchDTO = branchMapper.toDto(branch);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Branch found", branchDTO)
        );
    }

    // Get all Branches
    public ResponseEntity<ApiResponse<List<BranchDTO>>> getAllBranches() {
        List<Branch> branches = branchRepository.findAllByActiveTrue();

        List<BranchDTO> branchDTOs = branchMapper.toDtoList(branches);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Branches fetched successfully", branchDTOs)
        );
    }

    // Get all Branch names
    public ResponseEntity<ApiResponse<List<String>>> getListOfBranchNames() {
        List<Branch> branches = branchRepository.findAllByActiveTrue();
        List<String> branchNames = branches.stream()
                .map(Branch::getName)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success(HttpStatus.OK.value(), "Branch names fetched successfully", branchNames)
        );
    }
}
