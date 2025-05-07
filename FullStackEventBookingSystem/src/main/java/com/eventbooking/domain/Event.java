package com.eventbooking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * An Event.
 */
@Entity
@Table(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "agenda")
    private String agenda;

    @Column(name = "category")
    private String category;

    @NotNull(message = "date is mandatory")
    @Column(name = "date", nullable = false)
    @Future(message = "date must be in the future")
    private LocalDate date;

    @NotNull(message = "venue is mandatory")
    @Column(name = "venue", nullable = false)
    private String venue;

    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "image is mandatory")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public Event() {
    }

    public Event(Long id,
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

    public void setImageUrl(String imgUrl) {
        this.imageUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id != null && id.equals(event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", category='" + getCategory() + "'" +
            ", date='" + getDate() + "'" +
            ", venue='" + getVenue() + "'" +
            ", price=" + getPrice() +
            ", imgUrl='" + getImageUrl() + "'" +
            "}";
    }
}
