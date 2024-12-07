package com.example.emipos.mappers;

import com.example.emipos.dtos.StockItemDTO;
import com.example.emipos.models.StockItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockItemMapper {
    StockItemMapper INSTANCE = Mappers.getMapper(StockItemMapper.class);

    StockItemDTO toDto(StockItem stockItem);

    StockItem toEntity(StockItemDTO stockItemDTO);

    List<StockItemDTO> toDtoList(List<StockItem> stockItems);

    List<StockItem> toEntityList(List<StockItemDTO> stockItemDTOs);
}
