package com.taa.laf.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private String userName; //ToDo is this needed can we retrieve from user services or do we cash it?
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "lost_item_id")
    private LostItem lostItem;

    public Claim() {

    }

    public Claim(UUID userId, String userName, int quantity, LostItem lostItem) {
        this.userId = userId;
        this.userName = userName;
        this.quantity = quantity;
        this.lostItem = lostItem;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LostItem getLostItem() {
        return lostItem;
    }

    public void setLostItem(LostItem lostItem) {
        this.lostItem = lostItem;
    }
}