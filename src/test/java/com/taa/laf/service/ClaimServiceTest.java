package com.taa.laf.service;

import com.taa.laf.client.UserClient;
import com.taa.laf.controller.dto.request.ClaimRequestDto;
import com.taa.laf.domain.repository.ClaimRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClaimServiceTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private UserClient userClient;

    @Mock
    private LostItemService lostItemService;

    @InjectMocks
    private ClaimService claimService;

    private ClaimRequestDto claimRequestDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        claimRequestDto = new ClaimRequestDto();
        claimRequestDto.setLostItemId(UUID.randomUUID());
    }

    @Test
    public void testClaimLostItemLostItemNotFound() {
        // When
        when(lostItemService.getLostItemById(claimRequestDto.getLostItemId())).thenReturn(Optional.empty());

        // Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            claimService.claimLostItem(claimRequestDto);
        });
        assertEquals("Lost item not found", exception.getMessage());
    }
}
