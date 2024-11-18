package com.taa.laf.domain.repository;

import com.taa.laf.domain.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LostItemRepository extends JpaRepository<LostItem, UUID> {
}
