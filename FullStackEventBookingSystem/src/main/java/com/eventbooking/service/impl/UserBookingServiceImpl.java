package com.eventbooking.service.impl;

import com.eventbooking.service.*;
import com.eventbooking.service.dto.*;
import com.eventbooking.util.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserBookingServiceImpl implements UserBookingService {

    private final BookingService bookingService;

    private final EventService eventService;

    public UserBookingServiceImpl(BookingService bookingService,
                                  EventService eventService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @Override
    public BookingResponseDTO bookEvent(Long eventId) {
        UserDTO currentUser = SecurityUtil.getCurrentUser();
        if(currentUser == null) {
            return null;
        }
        EventDTO event = eventService.findOne(eventId).orElseThrow();

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUser(currentUser);
        bookingDTO.setEvent(event);

        BookingDTO savedBooking =  bookingService.save(bookingDTO);
        return new BookingResponseDTO(savedBooking);
    }

    @Override
    public Page<BookingDTO> getUserBookings(Pageable pageable) {
        UserDTO currentUser = SecurityUtil.getCurrentUser();
        if(currentUser == null) {
            return null;
        }
        String userEmail = currentUser.getEmail();
        return bookingService.getBookingsByUserEmail(userEmail, pageable);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        UserDTO currentUser = SecurityUtil.getCurrentUser();
        if(currentUser == null) {
            return;
        }
        String userEmail = currentUser.getEmail();
        bookingService.deleteByIdAndUserEmail(bookingId, userEmail);
    }
}
