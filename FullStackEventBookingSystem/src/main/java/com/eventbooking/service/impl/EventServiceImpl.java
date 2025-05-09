package com.eventbooking.service.impl;

import java.util.Objects;
import java.util.Optional;

import com.eventbooking.domain.Event;
import com.eventbooking.repository.EventRepository;
import com.eventbooking.service.EventService;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.HomePageEventDTO;
import com.eventbooking.service.mapper.EventMapper;
import com.eventbooking.web.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service Implementation for managing {@link com.eventbooking.domain.Event}.
 */
@Service
@Validated
@Transactional
public class EventServiceImpl implements EventService {

    private static final Logger LOG = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;


    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDTO save(EventDTO eventDTO) {
        LOG.debug("Request to save Event : {}", eventDTO);
        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    @Override
    public EventDTO update(Long id, EventDTO eventDTO) throws BadRequestAlertException {
        LOG.debug("Request to update Event : {}", eventDTO);

        checkUserInputs(id, eventDTO);

        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    @Override
    public Optional<EventDTO> partialUpdate(EventDTO eventDTO) {
        LOG.debug("Request to partially update Event : {}", eventDTO);

        return eventRepository
            .findById(eventDTO.getId())
            .map(existingEvent -> {
                eventMapper.partialUpdate(existingEvent, eventDTO);

                return existingEvent;
            })
            .map(eventRepository::save)
            .map(eventMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Events");
        return eventRepository.findAll(pageable).map(eventMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HomePageEventDTO> getHomePageEvents(Pageable pageable) {
        LOG.debug("Request to get all Events for home page");
        return eventRepository.findAllHomePageEvents(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EventDTO> findOne(Long id) {
        LOG.debug("Request to get Event : {}", id);
        return eventRepository.findById(id).map(eventMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Event : {}", id);
        eventRepository.deleteById(id);
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
