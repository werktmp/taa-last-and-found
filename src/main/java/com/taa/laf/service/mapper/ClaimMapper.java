package com.taa.laf.service.mapper;

import com.taa.laf.client.dto.User;
import com.taa.laf.controller.dto.request.ClaimRequestDto;
import com.taa.laf.controller.dto.response.ClaimDto;
import com.taa.laf.domain.entity.Claim;
import com.taa.laf.domain.entity.LostItem;

import java.util.List;
import java.util.stream.Collectors;

public class ClaimMapper {

    public static ClaimDto toDTO(Claim claim) {
        return new ClaimDto(claim.getUserId(), claim.getUserName(), claim.getQuantity(), LostItemMapper.toDTO(claim.getLostItem()));
    }

    public static List<ClaimDto> toDTOList(List<Claim> claims) {
        return claims.stream()
                .map(ClaimMapper::toDTO)
                .collect(Collectors.toList());
    }

    // to entity
    public static Claim toEntity(ClaimRequestDto claimRequestDto, User user, LostItem lostItem) {
        return new Claim(user.getUserId(), user.getUserName(), claimRequestDto.getQuantity(), lostItem);
    }
}
