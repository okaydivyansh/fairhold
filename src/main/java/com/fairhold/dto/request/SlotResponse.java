package com.fairhold.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SlotResponse {

    private Long id;

    private Long resourceId;

    private String resourceName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean available;
}