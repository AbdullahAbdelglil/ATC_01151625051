package com.eventbooking.web.rest;

import com.eventbooking.domain.Event;
import com.eventbooking.service.AdminService;
import com.eventbooking.service.BookingService;
import com.eventbooking.service.EventService;
import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.AdminViewEventDTO;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.web.errors.BadRequestAlertException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final EventService eventService;
    private final AdminService adminService;


    public AdminController(EventService eventService,
                           AdminService adminService) {
        this.eventService = eventService;
        this.adminService = adminService;
    }

    /**
     * Dashboard endpoint - Display all events with booking information
     * @return List of EventDTO with additional booking information
     */
    @GetMapping("/dashboard")
    public ResponseEntity<List<AdminViewEventDTO>> getDashboard(Pageable pageable) {
        Page<EventDTO> events = eventService.findAll(pageable);
        List<AdminViewEventDTO> eventDTOs = adminService.getAdminViewEvents(events.getContent());
        return ResponseEntity.ok(eventDTOs);
    }

    /**
     * Control Panel - CRUD operations for events
     */

    // Get event by ID
    @GetMapping("/control-panel/{id}")
    public ResponseEntity<AdminViewEventDTO> getEventById(@PathVariable Long id) {
        Optional<EventDTO> event = eventService.findOne(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adminService.getAdminViewDetailEvents(event.get()));
    }

    // Create new event
    @PostMapping("/control-panel")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO savedEvent = eventService.save(eventDTO);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // Update event
    @PutMapping("/control-panel/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) throws BadRequestAlertException {
        EventDTO updatedEvent = eventService.update(id, eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    // Delete event
    @DeleteMapping("/control-panel/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        // Check if event exists
        if (!eventService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
