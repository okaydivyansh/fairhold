package com.fairhold.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class HoldResponse {

    private Long slotId;

    private Long userId;

    private String status;

    private LocalDateTime expiresAt;

    private String message;
}