package de.dhbw.ase.timy.adapters.representations.mappers.booking;

import de.dhbw.ase.timy.adapters.representations.BookingDTO;
import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DTOToBookingMapper implements Function<BookingDTO, Booking> {

    @Autowired
    public DTOToBookingMapper() {
    }

    @Override
    public Booking apply(BookingDTO bookingDTO) {
    	return new Booking(
            bookingDTO.getId(),
            bookingDTO.getCategoryId(),
            bookingDTO.getProjectId(),
            bookingDTO.getTitle(),
            bookingDTO.getDescription(),
            bookingDTO.getStart(),
            bookingDTO.getEnd()
        );
    }

    public Booking update(Booking oldBooking, BookingDTO newBookingDTO) {
        oldBooking.setCategoryId(newBookingDTO.getCategoryId());
        oldBooking.setProjectId(newBookingDTO.getProjectId());
        oldBooking.setTitle(newBookingDTO.getTitle());
        oldBooking.setDescription(newBookingDTO.getDescription());
        oldBooking.setStart(newBookingDTO.getStart());
        oldBooking.setEnd(newBookingDTO.getEnd());
        return oldBooking;
}

}
