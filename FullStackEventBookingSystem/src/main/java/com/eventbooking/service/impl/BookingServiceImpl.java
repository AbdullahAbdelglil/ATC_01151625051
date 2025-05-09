package com.eventbooking.service.impl;

import com.eventbooking.domain.Booking;
import com.eventbooking.repository.BookingRepository;
import com.eventbooking.service.BookingService;
import com.eventbooking.service.dto.BookingDTO;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.service.mapper.BookingMapper;

import com.eventbooking.service.mapper.CustomBookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomBookingMapper customBookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              BookingMapper bookingMapper,
                              CustomBookingMapper customBookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.customBookingMapper = customBookingMapper;
    }

    @Override
    @Transactional
    public BookingDTO save(BookingDTO bookingDTO) {
        log.debug("Request to save Booking : {}", bookingDTO);
        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking = bookingRepository.save(booking);

        UserDTO userDTO = customBookingMapper.mapEmailToUserDTO(booking.getUserEmail());
        EventDTO eventDTO = customBookingMapper.mapIdToEventDTO(booking.getEventId());

        return new BookingDTO(booking.getId(), userDTO, eventDTO, booking.getBookingDate());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingDTO> getBookingsByUserEmail(String userEmail, Pageable pageable) {
        return bookingRepository.findByUserEmail(userEmail, pageable).map(bookingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingDTO> getAllBookings(Pageable pageable) {
        return null;
    }

    @Override
    public boolean existsByUserEmailAndEventId(String email, Long eventId) {
        return bookingRepository.existsByUserEmailAndEventId(email, eventId);
    }

    @Override
    @Transactional
    public void deleteByIdAndUserEmail(Long bookingId, String userEmail) {
        log.debug("Request to delete booking : {}", bookingId);
        bookingRepository.deleteByIdAndUserEmail(bookingId, userEmail);
    }

}
