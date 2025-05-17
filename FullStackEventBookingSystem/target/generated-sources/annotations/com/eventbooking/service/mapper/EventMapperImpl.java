package com.eventbooking.service.mapper;

import com.eventbooking.domain.Event;
import com.eventbooking.service.dto.EventDTO;
import com.eventbooking.service.dto.HomePageEventDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-17T14:01:58+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEntity(EventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Event event = new Event();

        event.setId( dto.getId() );
        event.setName( dto.getName() );
        event.setDescription( dto.getDescription() );
        event.setAgenda( dto.getAgenda() );
        event.setCategory( dto.getCategory() );
        event.setDate( dto.getDate() );
        event.setVenue( dto.getVenue() );
        event.setPrice( dto.getPrice() );
        event.setImageUrl( dto.getImageUrl() );
        event.setCreatedAt( dto.getCreatedAt() );
        event.setUpdatedAt( dto.getUpdatedAt() );

        return event;
    }

    @Override
    public EventDTO toDto(Event entity) {
        if ( entity == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        eventDTO.setId( entity.getId() );
        eventDTO.setName( entity.getName() );
        eventDTO.setDescription( entity.getDescription() );
        eventDTO.setAgenda( entity.getAgenda() );
        eventDTO.setCategory( entity.getCategory() );
        eventDTO.setDate( entity.getDate() );
        eventDTO.setVenue( entity.getVenue() );
        eventDTO.setPrice( entity.getPrice() );
        eventDTO.setImageUrl( entity.getImageUrl() );
        eventDTO.setCreatedAt( entity.getCreatedAt() );
        eventDTO.setUpdatedAt( entity.getUpdatedAt() );

        return eventDTO;
    }

    @Override
    public List<Event> toEntity(List<EventDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( dtoList.size() );
        for ( EventDTO eventDTO : dtoList ) {
            list.add( toEntity( eventDTO ) );
        }

        return list;
    }

    @Override
    public List<EventDTO> toDto(List<Event> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>( entityList.size() );
        for ( Event event : entityList ) {
            list.add( toDto( event ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Event entity, EventDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
        if ( dto.getAgenda() != null ) {
            entity.setAgenda( dto.getAgenda() );
        }
        if ( dto.getCategory() != null ) {
            entity.setCategory( dto.getCategory() );
        }
        if ( dto.getDate() != null ) {
            entity.setDate( dto.getDate() );
        }
        if ( dto.getVenue() != null ) {
            entity.setVenue( dto.getVenue() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getImageUrl() != null ) {
            entity.setImageUrl( dto.getImageUrl() );
        }
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            entity.setUpdatedAt( dto.getUpdatedAt() );
        }
    }

    @Override
    public HomePageEventDTO toHomePageEventDTO(Event event) {
        if ( event == null ) {
            return null;
        }

        Long id = null;
        String imageUrl = null;
        String description = null;
        LocalDate date = null;
        BigDecimal price = null;

        id = event.getId();
        imageUrl = event.getImageUrl();
        description = event.getDescription();
        date = event.getDate();
        price = event.getPrice();

        String title = null;
        Integer categoryId = null;
        boolean booked = false;

        HomePageEventDTO homePageEventDTO = new HomePageEventDTO( id, title, description, categoryId, imageUrl, date, price, booked );

        if ( event.getCategory() != null ) {
            homePageEventDTO.setCategory( String.valueOf( event.getCategory() ) );
        }

        return homePageEventDTO;
    }
}
