package com.fairhold.service;

import com.fairhold.dto.request.CreateSlotRequest;
import com.fairhold.dto.response.SlotResponse;

import java.util.List;

public interface SlotService {

    SlotResponse createSlot(CreateSlotRequest request);

    List<SlotResponse> getAllSlots();

    List<SlotResponse> getSlotsByResourceId(Long resourceId);
}