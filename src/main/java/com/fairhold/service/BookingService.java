package com.fairhold.service;

import com.fairhold.dto.request.ConfirmBookingRequest;
import com.fairhold.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse confirmBooking(ConfirmBookingRequest request);

    List<BookingResponse> getBookingsByUser(Long userId);

    BookingResponse cancelBooking(Long bookingId);

    List<BookingResponse> getAllBookings();
}