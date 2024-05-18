package de.dhbw.ase.timy.domain.entities.report.bookingrep;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingReportRepresentationBridgeRepository {
    void save (BookingReportRepresentation bookingReportRepresentation);
    Optional<BookingReportRepresentation> findById(int id);
    List<BookingReportRepresentation> findByReportId(int reportId);
}
