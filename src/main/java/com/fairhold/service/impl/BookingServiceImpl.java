package com.fairhold.service.impl;

import com.fairhold.dto.request.ConfirmBookingRequest;
import com.fairhold.dto.response.BookingResponse;
import com.fairhold.entity.Booking;
import com.fairhold.entity.BookingStatus;
import com.fairhold.entity.Slot;
import com.fairhold.entity.User;
import com.fairhold.exception.*;
import com.fairhold.repository.BookingRepository;
import com.fairhold.repository.SlotRepository;
import com.fairhold.repository.UserRepository;
import com.fairhold.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;
    private final UserRepository userRepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public BookingResponse confirmBooking(ConfirmBookingRequest request) {

        Slot slot = slotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new SlotNotFoundException("Slot not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (bookingRepository.existsBySlotIdAndStatus(request.getSlotId(), BookingStatus.CONFIRMED)) {
            throw new SlotAlreadyBookedException("Slot is already booked");
        }

        String holdKey = buildHoldKey(request.getSlotId());

        String heldByUserId = stringRedisTemplate.opsForValue().get(holdKey);

        if (heldByUserId == null) {
            throw new HoldNotFoundException("Hold has expired or does not exist");
        }

        if (!heldByUserId.equals(request.getUserId().toString())) {
            throw new HoldOwnershipException("This slot is held by another user");
        }

        Booking booking = Booking.builder()
                .user(user)
                .slot(slot)
                .bookedAt(LocalDateTime.now())
                .status(BookingStatus.CONFIRMED)
                .build();

        Booking savedBooking = bookingRepository.save(booking);

        slot.setAvailable(false);
        slotRepository.save(slot);

        stringRedisTemplate.delete(holdKey);

        return mapToResponse(savedBooking, "Booking confirmed successfully.");
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {

        return bookingRepository.findByUserId(userId)
                .stream()
                .map(booking -> mapToResponse(booking, "Booking fetched successfully."))
                .toList();
    }

    private String buildHoldKey(Long slotId) {
        return "hold:slot:" + slotId;
    }

    private BookingResponse mapToResponse(Booking booking, String message) {

        Slot slot = booking.getSlot();

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .userId(booking.getUser().getId())
                .userEmail(booking.getUser().getEmail())
                .slotId(slot.getId())
                .resourceName(slot.getResource().getName())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .bookedAt(booking.getBookedAt())
                .status(booking.getStatus().name())
                .message(message)
                .build();
    }

    @Override
    @Transactional
    public BookingResponse cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            return mapToResponse(booking, "Booking is already cancelled.");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Slot slot = booking.getSlot();
        slot.setAvailable(true);

        slotRepository.save(slot);

        Booking savedBooking = bookingRepository.save(booking);

        return mapToResponse(savedBooking, "Booking cancelled successfully.");
    }
}