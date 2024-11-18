package com.taa.laf.controller.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ClaimRequestDto {
    @NotNull(message = "Lost Item ID cannot be null")
    private UUID lostItemId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;


    public UUID getLostItemId() {
        return lostItemId;
    }

    public void setLostItemId(UUID lostItemId) {
        this.lostItemId = lostItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
