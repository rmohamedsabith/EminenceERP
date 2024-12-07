package com.example.emipos.mappers;

import com.example.emipos.dtos.BrandDTO;
import com.example.emipos.models.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    // Convert Brand entity to BrandDTO
    BrandDTO toDto(Brand brand);

    // Convert BrandDTO to Brand entity
    Brand toEntity(BrandDTO brandDTO);

    // Convert List<Brand> to List<BrandDTO>
    List<BrandDTO> toDtoList(List<Brand> mainCategories);

    // Convert List<BrandDTO> to List<Brand>
    List<Brand> toEntityList(List<BrandDTO> brandDTOs);
}
