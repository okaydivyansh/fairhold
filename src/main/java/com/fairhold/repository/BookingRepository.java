package com.fairhold.repository;

import com.fairhold.entity.Booking;
import com.fairhold.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsBySlotIdAndStatus(Long slotId, BookingStatus status);

    List<Booking> findByUserId(Long userId);
}