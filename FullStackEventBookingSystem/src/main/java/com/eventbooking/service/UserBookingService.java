package com.eventbooking.service;

import com.eventbooking.service.dto.BookingDTO;
import com.eventbooking.service.dto.BookingResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserBookingService {

    BookingResponseDTO bookEvent(Long eventId);

    Page<BookingDTO> getUserBookings(Pageable pageable);

    void cancelBooking(Long bookingId);

}
