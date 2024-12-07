package com.example.emipos.mappers;

import com.example.emipos.models.MainCategory;
import com.example.emipos.dtos.MainCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MainCategoryMapper {

    MainCategoryMapper INSTANCE = Mappers.getMapper(MainCategoryMapper.class);

    // Convert MainCategory entity to MainCategoryDTO
    MainCategoryDTO toDto(MainCategory mainCategory);

    // Convert MainCategoryDTO to MainCategory entity
    MainCategory toEntity(MainCategoryDTO mainCategoryDTO);

    // Convert List<MainCategory> to List<MainCategoryDTO>
    List<MainCategoryDTO> toDtoList(List<MainCategory> mainCategories);

    // Convert List<MainCategoryDTO> to List<MainCategory>
    List<MainCategory> toEntityList(List<MainCategoryDTO> mainCategoryDTOs);
}
