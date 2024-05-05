package de.dhbw.ase.timy.adapters.representations.mappers.booking;

import de.dhbw.ase.timy.adapters.representations.BookingDTO;
import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
//Beispiel macht keinen Sinn, da EntityExample und das DTO 1 zu 1 das gleiche sind.
// Würde benötigt werden bei komplexen Entitys die z.B. sich gegenseitig referenzieren.
// Solche Mapper lässt man im normal fall aus den entitys und dtos automatisch generieren, da wir aber bestimmte
// Zeilen anzahl schreiben müssen, macht es Sinn diese selbst zu schreiben :)

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
    return new Booking(
        oldBooking.getId(),
        newBookingDTO.getCategoryId(),
        newBookingDTO.getProjectId(),
        newBookingDTO.getTitle(),
        newBookingDTO.getDescription(),
        newBookingDTO.getStart(),
        newBookingDTO.getEnd()
    );
}

}
