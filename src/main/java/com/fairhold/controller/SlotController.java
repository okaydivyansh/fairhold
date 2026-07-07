package com.fairhold.controller;

import com.fairhold.dto.request.CreateSlotRequest;
import com.fairhold.dto.response.SlotResponse;
import com.fairhold.service.SlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @PostMapping
    public ResponseEntity<SlotResponse> createSlot(
            @Valid @RequestBody CreateSlotRequest request) {

        SlotResponse response = slotService.createSlot(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<SlotResponse>> getAllSlots() {

        List<SlotResponse> response = slotService.getAllSlots();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<List<SlotResponse>> getSlotsByResourceId(
            @PathVariable Long resourceId) {

        List<SlotResponse> response = slotService.getSlotsByResourceId(resourceId);

        return ResponseEntity.ok(response);
    }
}