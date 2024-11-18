package com.taa.laf.controller;

import com.taa.laf.controller.dto.request.ClaimRequestDto;
import com.taa.laf.controller.dto.response.ClaimDto;
import com.taa.laf.service.ClaimService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/claim")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Requirement 3: Retrieved LostItems claimed by people (ADMIN)
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClaimDto>> getClaims() {
        List<ClaimDto> claims = claimService.getClaims();
        return ResponseEntity.ok(claims);
    }

    // Requirement 3: Claim LostItem Data and save (USER)
    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> claimLostItem(@RequestBody @Valid ClaimRequestDto claimRequest) {
        claimService.claimLostItem(claimRequest);
        //ToDo error handeling
        return ResponseEntity.ok("Claim registered successfully");
    }
}

