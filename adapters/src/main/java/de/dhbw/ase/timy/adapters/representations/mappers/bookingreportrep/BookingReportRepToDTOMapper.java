package de.dhbw.ase.timy.adapters.representations.mappers.bookingreportrep;

import de.dhbw.ase.timy.adapters.representations.BookingReportRepresentationDTO;
import de.dhbw.ase.timy.domain.entities.report.bookingrep.BookingReportRepresentation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookingReportRepToDTOMapper implements Function<BookingReportRepresentation, BookingReportRepresentationDTO> {

    @Override
    public BookingReportRepresentationDTO apply(BookingReportRepresentation bookingReportRepresentation) {
        return new BookingReportRepresentationDTO(
                bookingReportRepresentation.getId(),
                //bookingReportRepresentation.getReportId(),
                bookingReportRepresentation.getBookingId(),
                bookingReportRepresentation.getCategoryId(),
                bookingReportRepresentation.getCategoryName(),
                bookingReportRepresentation.getProjectId(),
                bookingReportRepresentation.getProjectName(),
                bookingReportRepresentation.getTitle(),
                bookingReportRepresentation.getDescription(),
                bookingReportRepresentation.getStart(),
                bookingReportRepresentation.getEnd(),
                bookingReportRepresentation.getBookingDuration(),
                bookingReportRepresentation.getBookingCreated(),
                bookingReportRepresentation.getBookingUpdated()
        );
    }
}
