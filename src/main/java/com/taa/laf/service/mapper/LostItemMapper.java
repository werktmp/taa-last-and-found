package com.taa.laf.service.mapper;

import com.taa.laf.controller.dto.response.LostItemDto;
import com.taa.laf.domain.entity.LostItem;

import java.util.List;
import java.util.stream.Collectors;

public class LostItemMapper {

    public static LostItemDto toDTO(LostItem lostItem) {
        return new LostItemDto(lostItem.getId(), lostItem.getItemName(), lostItem.getQuantity(), lostItem.getPlace());
    }

    public static List<LostItemDto> toDTOList(List<LostItem> lostItems) {
        return lostItems.stream()
                .map(LostItemMapper::toDTO)
                .collect(Collectors.toList());
    }
}
