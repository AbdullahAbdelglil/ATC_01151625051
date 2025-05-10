package com.eventbooking.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.PrePersist;

import java.util.Date;

public class BookingDTO {
    private Long id;
    private UserDTO user;
    private EventDTO event;
    private Date bookingDate;

    public BookingDTO(Long id, UserDTO user, EventDTO event, Date bookingDate) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.bookingDate = bookingDate;
    }

    public BookingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    @PrePersist
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = new Date(System.currentTimeMillis());
    }
}
