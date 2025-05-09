package com.eventbooking.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EventDetailsDTO extends EventDTO {

    public EventDetailsDTO(Long id,
                           String name,
                           String description,
                           String agenda,
                           String category,
                           LocalDate date,
                           String venue,
                           BigDecimal price,
                           String imageUrl,
                           boolean booked) {
        super(id, name, description, agenda, category, date, venue, price, imageUrl);
        this.booked = booked;
    }

    private boolean booked;

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
