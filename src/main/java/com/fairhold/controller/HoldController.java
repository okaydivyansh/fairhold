package com.fairhold.controller;

import com.fairhold.dto.request.CreateHoldRequest;
import com.fairhold.dto.response.HoldResponse;
import com.fairhold.service.HoldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/holds")
@RequiredArgsConstructor
public class HoldController {

    private final HoldService holdService;

    @PostMapping
    public ResponseEntity<HoldResponse> createHold(
            @Valid @RequestBody CreateHoldRequest request) {

        HoldResponse response = holdService.createHold(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}