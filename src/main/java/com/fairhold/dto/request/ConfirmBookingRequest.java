package com.fairhold.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmBookingRequest {

    @NotNull(message = "Slot id is required")
    private Long slotId;

    @NotNull(message = "User id is required")
    private Long userId;
}