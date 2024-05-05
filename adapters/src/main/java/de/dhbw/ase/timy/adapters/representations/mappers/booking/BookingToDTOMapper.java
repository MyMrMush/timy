package de.dhbw.ase.timy.adapters.representations.mappers.booking;

import de.dhbw.ase.timy.adapters.representations.BookingDTO;
import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.function.Function;

//Beispiel macht keinen Sinn, da EntityExample und das DTO 1 zu 1 das gleiche sind.
//Würde benötigt werden bei komplexen Entitys die z.B. sich gegenseitig referenzieren.
//Solche Mapper lässt man im normal fall aus den entitys und dtos automatisch generieren, da wir aber bestimmte
//Zeilen anzahl schreiben müssen, macht es Sinn diese selbst zu schreiben :)
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
