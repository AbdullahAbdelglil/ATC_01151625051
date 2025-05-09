package com.eventbooking.service;

import com.eventbooking.service.dto.BookingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    //Save booking
    BookingDTO save(BookingDTO bookingDTO);

    Page<BookingDTO> getBookingsByUserEmail(String userEmail, Pageable pageable);

    // For user only: User can delete his booking
    void deleteByIdAndUserEmail(Long bookingId, String userEmail);

    //For Admin only: get all bookings in the system
    Page<BookingDTO> getAllBookings(Pageable pageable);

    boolean existsByUserEmailAndEventId(String email, Long eventId);
}
