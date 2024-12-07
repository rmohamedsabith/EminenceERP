package com.example.emipos.mappers;

import com.example.emipos.dtos.CategoryDTO;
import com.example.emipos.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // Convert Category entity to CategoryDTO
    CategoryDTO toDto(Category category);

    // Convert CategoryDTO to Category entity
    Category toEntity(CategoryDTO categoryDTO);

    // Convert List<Category> to List<CategoryDTO>
    List<CategoryDTO> toDtoList(List<Category> categories);

    // Convert List<CategoryDTO> to List<Category>
    List<Category> toEntityList(List<CategoryDTO> categoryDTOs);
}
