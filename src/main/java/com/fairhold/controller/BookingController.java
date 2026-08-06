package com.fairhold.controller;

import com.fairhold.dto.request.ConfirmBookingRequest;
import com.fairhold.dto.response.BookingResponse;
import com.fairhold.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/confirm")
    public ResponseEntity<BookingResponse> confirmBooking(
            @Valid @RequestBody ConfirmBookingRequest request) {

        BookingResponse response = bookingService.confirmBooking(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUser(
            @PathVariable Long userId) {

        List<BookingResponse> response = bookingService.getBookingsByUser(userId);

        return ResponseEntity.ok(response);
    }
}