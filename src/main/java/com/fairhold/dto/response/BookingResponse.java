package com.fairhold.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookingResponse {

    private Long bookingId;

    private Long userId;

    private String userEmail;

    private Long slotId;

    private String resourceName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime bookedAt;

    private String status;

    private String message;
}