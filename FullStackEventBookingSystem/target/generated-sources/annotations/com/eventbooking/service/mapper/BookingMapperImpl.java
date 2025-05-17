package com.eventbooking.service.mapper;

import com.eventbooking.domain.Booking;
import com.eventbooking.service.dto.BookingDTO;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.service.dto.UserViewEventDetailsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-17T15:39:07+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Autowired
    private CustomBookingMapper customBookingMapper;

    @Override
    public List<Booking> toEntity(List<BookingDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Booking> list = new ArrayList<Booking>( dtoList.size() );
        for ( BookingDTO bookingDTO : dtoList ) {
            list.add( toEntity( bookingDTO ) );
        }

        return list;
    }

    @Override
    public List<BookingDTO> toDto(List<Booking> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BookingDTO> list = new ArrayList<BookingDTO>( entityList.size() );
        for ( Booking booking : entityList ) {
            list.add( toDto( booking ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Booking entity, BookingDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getBookingDate() != null ) {
            entity.setBookingDate( dto.getBookingDate() );
        }
    }

    @Override
    public BookingDTO toDto(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setUser( customBookingMapper.mapEmailToUserDTO( booking.getUserEmail() ) );
        bookingDTO.setEvent( customBookingMapper.mapIdToEventDetailsDTO( booking.getEventId() ) );
        bookingDTO.setId( booking.getId() );
        bookingDTO.setBookingDate( booking.getBookingDate() );

        return bookingDTO;
    }

    @Override
    public Booking toEntity(BookingDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setUserEmail( dtoUserEmail( dto ) );
        booking.setEventId( dtoEventId( dto ) );
        booking.setId( dto.getId() );
        booking.setBookingDate( dto.getBookingDate() );

        return booking;
    }

    private String dtoUserEmail(BookingDTO bookingDTO) {
        UserDTO user = bookingDTO.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getEmail();
    }

    private Long dtoEventId(BookingDTO bookingDTO) {
        UserViewEventDetailsDTO event = bookingDTO.getEvent();
        if ( event == null ) {
            return null;
        }
        return event.getId();
    }
}
