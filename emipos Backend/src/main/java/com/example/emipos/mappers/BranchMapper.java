package com.example.emipos.mappers;

import com.example.emipos.dtos.BranchDTO;
import com.example.emipos.models.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    // Convert Branch entity to BranchDTO
    BranchDTO toDto(Branch brand);

    // Convert BranchDTO to Branch entity
    Branch toEntity(BranchDTO branchDTO);

    // Convert List<Branch> to List<BranchDTO>
    List<BranchDTO> toDtoList(List<Branch> mainCategories);

    // Convert List<BranchDTO> to List<Branch>
    List<Branch> toEntityList(List<BranchDTO> branchDTOs);
}
