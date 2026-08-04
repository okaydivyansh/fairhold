package com.fairhold.service.impl;

import com.fairhold.dto.request.CreateHoldRequest;
import com.fairhold.dto.response.HoldResponse;
import com.fairhold.entity.Slot;
import com.fairhold.exception.SlotAlreadyHeldException;
import com.fairhold.exception.SlotNotFoundException;
import com.fairhold.exception.SlotUnavailableException;
import com.fairhold.repository.SlotRepository;
import com.fairhold.service.HoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HoldServiceImpl implements HoldService {

    private static final long HOLD_EXPIRY_MINUTES = 5;

    private final SlotRepository slotRepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public HoldResponse createHold(CreateHoldRequest request) {

        Slot slot = slotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new SlotNotFoundException("Slot not found"));

        if (!slot.isAvailable()) {
            throw new SlotUnavailableException("Slot is not available");
        }

        String holdKey = buildHoldKey(request.getSlotId());

        Boolean holdCreated = stringRedisTemplate.opsForValue()
                .setIfAbsent(
                        holdKey,
                        request.getUserId().toString(),
                        Duration.ofMinutes(HOLD_EXPIRY_MINUTES)
                ); //this means -> Create this hold only if no hold already exists.

        if (Boolean.FALSE.equals(holdCreated)) {
            throw new SlotAlreadyHeldException("Slot is already temporarily held");
        }

        return HoldResponse.builder()
                .slotId(request.getSlotId())
                .userId(request.getUserId())
                .status("HELD")
                .expiresAt(LocalDateTime.now().plusMinutes(HOLD_EXPIRY_MINUTES))
                .message("Slot held successfully for 5 minutes.")
                .build();
    }

    private String buildHoldKey(Long slotId) {
        return "hold:slot:" + slotId;
    }
}