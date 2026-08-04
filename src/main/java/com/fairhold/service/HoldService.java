package com.fairhold.service;

import com.fairhold.dto.request.CreateHoldRequest;
import com.fairhold.dto.response.HoldResponse;

public interface HoldService {

    HoldResponse createHold(CreateHoldRequest request);
}