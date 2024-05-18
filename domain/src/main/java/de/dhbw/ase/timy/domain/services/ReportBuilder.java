package de.dhbw.ase.timy.domain.services;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import de.dhbw.ase.timy.domain.entities.category.category.Category;
import de.dhbw.ase.timy.domain.entities.report.bookingrep.BookingReportRepresentation;
import de.dhbw.ase.timy.domain.entities.report.report.Report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class ReportBuilder {
    public static Report fromBookings(String name, String description, LocalDate start, LocalDate end, List<Booking> bookings, List<Category> categories) {
        List<BookingReportRepresentation> bookingRepresentations = BookingReportRepresentationBuilder.fromBookings(bookings, categories);
        Long totalDuration = bookingRepresentations.stream().map(BookingReportRepresentation::getBookingDuration).reduce(0L, Long::sum);
        return new Report(name, description, start, end, bookingRepresentations, totalDuration, LocalDateTime.now());
    }
}
