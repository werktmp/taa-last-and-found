package com.taa.laf.controller.dto.response;

import java.util.UUID;

public class ClaimDto {
    private final UUID userId;
    private final String userName;
    private final int quantity;
    private final LostItemDto lostItem;

    public ClaimDto(UUID userId, String userName, int quantity, LostItemDto lostItem) {
        this.userId = userId;
        this.userName = userName;
        this.quantity = quantity;
        this.lostItem = lostItem;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LostItemDto getLostItem() {
        return lostItem;
    }
}
