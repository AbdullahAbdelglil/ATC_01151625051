package com.eventbooking.util;

import com.eventbooking.service.BookingService;
import com.eventbooking.service.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class HomePageUtil {

    private static BookingService bookingService;

    public HomePageUtil(BookingService bookingService) {
        HomePageUtil.bookingService = bookingService;
    }

    public static boolean bookedEvent(Long eventId) {
        UserDTO userDTO = SecurityUtil.getCurrentUser();
        if (userDTO == null) {
            return false;
        }
        return bookingService.existsByUserEmailAndEventId(userDTO.getEmail(), eventId);
    }
}
