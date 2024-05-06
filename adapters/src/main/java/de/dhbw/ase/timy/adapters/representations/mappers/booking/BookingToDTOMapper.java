package de.dhbw.ase.timy.adapters.representations.mappers.booking;

import de.dhbw.ase.timy.adapters.representations.BookingDTO;
import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookingToDTOMapper implements Function<Booking, BookingDTO> {

    @Lazy
    @Autowired
    public BookingToDTOMapper() {

    }

    @Override
    public BookingDTO apply(Booking booking) {
       return new BookingDTO(
    		   booking.getId(),
               booking.getCategoryId(),
               booking.getProjectId(),
               booking.getTitle(),
                booking.getDescription(),
                booking.getStart(),
                booking.getEnd()
       );
    }
}
