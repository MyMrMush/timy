package de.dhbw.ase.timy.adapters.representations.mappers.report;

import de.dhbw.ase.timy.adapters.representations.ReportDTO;
import de.dhbw.ase.timy.adapters.representations.mappers.bookingreportrep.BookingReportRepToDTOMapper;
import de.dhbw.ase.timy.domain.entities.report.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReportToDTOMapper implements Function<Report, ReportDTO> {

    private final BookingReportRepToDTOMapper bookingReportRepToDTOMapper;

    @Autowired
    public ReportToDTOMapper(BookingReportRepToDTOMapper bookingReportRepToDTOMapper) {
        this.bookingReportRepToDTOMapper = bookingReportRepToDTOMapper;
    }

    @Override
    public ReportDTO apply(Report report) {
        return new ReportDTO(
                report.getId(),
                report.getName(),
                report.getDescription(),
                report.getStart(),
                report.getEnd(),
                report.getBookings().stream().map(bookingReportRepToDTOMapper).toList(),
                report.getTotalDuration(),
                report.getCreated()
        );
    }
}
