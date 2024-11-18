package com.taa.laf.service;

import com.taa.laf.client.UserClient;
import com.taa.laf.controller.dto.request.ClaimRequestDto;
import com.taa.laf.controller.dto.response.ClaimDto;
import com.taa.laf.domain.entity.Claim;
import com.taa.laf.domain.entity.LostItem;
import com.taa.laf.domain.repository.ClaimRepository;
import com.taa.laf.service.mapper.ClaimMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;
    private final UserClient userClient;
    private final LostItemService lostItemService;

    public ClaimService(ClaimRepository claimRepository, UserClient userClient, LostItemService lostItemService) {
        this.claimRepository = claimRepository;
        this.userClient = userClient;
        this.lostItemService = lostItemService;
    }

    public void claimLostItem(ClaimRequestDto claimRequest) {
        Optional<LostItem> lostItem = lostItemService.getLostItemById(claimRequest.getLostItemId());

        if (lostItem.isEmpty()) {
            throw new RuntimeException("Lost item not found");
        }
        //ToDo get use jwt token for user
        Claim claim = ClaimMapper.toEntity(claimRequest, userClient.getUserByUsername("Test User"), lostItem.get());
        claimRepository.save(claim);
    }

    public List<ClaimDto> getClaims() {
        return ClaimMapper.toDTOList(claimRepository.findAll());
    }
}