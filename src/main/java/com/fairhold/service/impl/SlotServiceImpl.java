package com.fairhold.service.impl;

import com.fairhold.dto.request.CreateSlotRequest;
import com.fairhold.dto.response.SlotResponse;
import com.fairhold.entity.Resource;
import com.fairhold.entity.Slot;
import com.fairhold.exception.InvalidSlotException;
import com.fairhold.exception.ResourceNotFoundException;
import com.fairhold.repository.ResourceRepository;
import com.fairhold.repository.SlotRepository;
import com.fairhold.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public SlotResponse createSlot(CreateSlotRequest request) {

        Resource resource = resourceRepository.findById(request.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new InvalidSlotException("End time must be after start time");
        }

        Slot slot = Slot.builder()
                .resource(resource)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .available(true)
                .build();

        Slot savedSlot = slotRepository.save(slot);

        return mapToResponse(savedSlot);
    }

    @Override
    public List<SlotResponse> getAllSlots() {

        return slotRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<SlotResponse> getSlotsByResourceId(Long resourceId) {

        return slotRepository.findByResourceId(resourceId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SlotResponse mapToResponse(Slot slot) {

        return SlotResponse.builder()
                .id(slot.getId())
                .resourceId(slot.getResource().getId())
                .resourceName(slot.getResource().getName())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .available(slot.isAvailable())
                .build();
    }
}