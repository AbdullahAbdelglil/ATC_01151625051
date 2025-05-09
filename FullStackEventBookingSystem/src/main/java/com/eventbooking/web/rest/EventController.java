package com.eventbooking.web.rest;

import com.eventbooking.repository.EventRepository;
import com.eventbooking.service.EventService;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.HomePageEventDTO;
import com.eventbooking.util.ResponseUtil;
import com.eventbooking.web.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.eventbooking.domain.Event}.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    private final EventRepository eventRepository;

    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @PostMapping("")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException, BadRequestAlertException {
        LOG.debug("REST request to save Event : {}", eventDTO);
        if (eventDTO.getId() != null) {
            LOG.error("err {}", eventDTO);
            throw new BadRequestAlertException("A new event cannot already have an ID", "id-exists");
        }
        eventDTO = eventService.save(eventDTO);
        return ResponseEntity.created(new URI("/api/events/" + eventDTO.getId())).body(eventDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EventDTO eventDTO
    ) throws BadRequestAlertException {
        LOG.debug("REST request to update Event : {}, {}", id, eventDTO);

        checkUserInputs(id, eventDTO);

        eventDTO = eventService.update(id, eventDTO);
        return ResponseEntity.ok().body(eventDTO);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EventDTO> partialUpdateEvent(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EventDTO eventDTO
    ) throws BadRequestAlertException {
        LOG.debug("REST request to partial update Event partially : {}, {}", id, eventDTO);
        checkUserInputs(id, eventDTO);
        Optional<EventDTO> result = eventService.partialUpdate(eventDTO);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @GetMapping("/details")
    public ResponseEntity<List<EventDTO>> getAllEvents(Pageable pageable) {
        LOG.debug("REST request to get a page of Events");
        Page<EventDTO> page = eventService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Event : {}", id);
        Optional<EventDTO> eventDTO = eventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Event : {}", id);
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void checkUserInputs(Long id, EventDTO eventDTO) throws BadRequestAlertException {
        if (eventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id",  "id-null");
        }
        if (!Objects.equals(id, eventDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID",  "id-invalid");
        }

        if (!eventRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", "id-notfound");
        }
    }
}
