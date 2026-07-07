package com.fairhold.repository;

import com.fairhold.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
//Find all slots where slot.resource.id = resourceId
    List<Slot> findByResourceId(Long resourceId);
}