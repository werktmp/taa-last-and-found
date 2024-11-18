package com.taa.laf.controller.dto.response;

import java.util.UUID;

public class LostItemDto {
    private final UUID id;
    private final String itemName;
    private final int quantity;
    private final String place;

    public LostItemDto(UUID id, String itemName, int quantity, String place) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.place = place;
    }

    public UUID getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPlace() {
        return place;
    }

}
