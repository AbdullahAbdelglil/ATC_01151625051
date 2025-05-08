package com.eventbooking.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.eventbooking.domain.Event} entity.
 */
public class EventDTO implements Serializable {

    private Long id;

    @NotNull(message = "is mandatory")
    @NotBlank(message = "not allowed to be blank")
    private String name;

    @NotNull(message = "is mandatory")
    private String description;

    @Lob
    private String agenda;

    private String category;

    @NotNull(message = "is required")
    @Future(message = "must be in the future")
    private LocalDate date;

    @NotNull(message = "is required")
    private String venue;

    private BigDecimal price = new BigDecimal("0.0");

    @NotBlank(message = "is required")
    private String imageUrl;

    public EventDTO() {
    }

    public EventDTO(Long id,
                    String name,
                    String description,
                    String agenda,
                    String category,
                    LocalDate date,
                    String venue,
                    BigDecimal price,
                    String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.agenda = agenda;
        this.category = category;
        this.date = date;
        this.venue = venue;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", category='" + getCategory() + "'" +
            ", date='" + getDate() + "'" +
            ", venue='" + getVenue() + "'" +
            ", price=" + getPrice() +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
