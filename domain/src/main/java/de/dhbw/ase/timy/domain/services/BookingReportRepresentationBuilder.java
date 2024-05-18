package de.dhbw.ase.timy.domain.services;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import de.dhbw.ase.timy.domain.entities.category.category.Category;
import de.dhbw.ase.timy.domain.entities.report.bookingrep.BookingReportRepresentation;

import java.util.List;

public abstract class BookingReportRepresentationBuilder {
    public static BookingReportRepresentation fromBooking(Booking booking, Category category) {
        BookingReportRepresentation bookingReportRepresentation = new BookingReportRepresentation();
        bookingReportRepresentation.setBookingId(booking.getId());
        bookingReportRepresentation.setCategoryId(booking.getCategoryId());
        bookingReportRepresentation.setCategoryName(category.getName());
        bookingReportRepresentation.setProjectId(booking.getProjectId());
        bookingReportRepresentation.setProjectName(category.getProjects().stream().filter(project -> project.getId() == booking.getProjectId()).findFirst().get().getName());
        bookingReportRepresentation.setTitle(booking.getTitle());
        bookingReportRepresentation.setDescription(booking.getDescription());
        bookingReportRepresentation.setStart(booking.getStart());
        bookingReportRepresentation.setEnd(booking.getEnd());
        bookingReportRepresentation.setBookingDuration(booking.getDuration());
        bookingReportRepresentation.setBookingCreated(booking.getCreated());
        bookingReportRepresentation.setBookingUpdated(booking.getUpdated());
        return bookingReportRepresentation;
    }

    public static List<BookingReportRepresentation> fromBookings(List<Booking> bookings, List<Category> categories){
        return bookings.stream().map(booking -> {
            Category category = categories.stream().filter(cat -> cat.getId() == booking.getCategoryId()).findFirst().get();
            return fromBooking(booking, category);
        }).toList();
    }
}
