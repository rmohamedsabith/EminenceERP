package com.example.emipos.mappers;

import com.example.emipos.dtos.SupplierDTO;
import com.example.emipos.models.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    // Convert Supplier to SupplierDTO
    SupplierDTO toDto(Supplier supplier);

    // Convert SupplierDTO to Supplier
    Supplier toEntity(SupplierDTO supplierDTO);

    // Convert List of Supplier to List of SupplierDTO
    List<SupplierDTO> toDtoList(List<Supplier> suppliers);
}
