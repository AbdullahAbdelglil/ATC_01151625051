package com.eventbooking.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserViewEventDetailsDTO extends EventDTO {

    public UserViewEventDetailsDTO(EventDTO eventDTO) {
        super(eventDTO.getId(),
                eventDTO.getName(),
                eventDTO.getDescription(),
                eventDTO.getAgenda(),
                eventDTO.getCategory(),
                eventDTO.getDate(),
                eventDTO.getVenue(),
                eventDTO.getPrice(),
                eventDTO.getImageUrl());
    }

    private boolean booked;

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
