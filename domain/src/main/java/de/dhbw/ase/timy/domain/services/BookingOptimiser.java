package de.dhbw.ase.timy.domain.services;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BookingOptimiser {
    public static List<Booking> optimiseBookings(List<Booking> bookings) {
        return bookings.stream()
                .collect(Collectors.groupingBy(booking -> booking.getStart().toLocalDate()))
                .values().stream()
                .flatMap(dayBookings -> optimiseDayBookings(dayBookings).stream())
                .collect(Collectors.toList());
    }

    private static List<Booking> optimiseDayBookings(List<Booking> dayBookings) {
        // Step 1: Remember the earliest start time of a booking
        LocalDateTime earliestStart = dayBookings.stream()
                .min(Comparator.comparing(Booking::getStart))
                .orElseThrow(IllegalArgumentException::new)
                .getStart();

        // Step 2: Sort bookings by category and project
        dayBookings.sort(Comparator.comparing(Booking::getCategoryId)
                .thenComparing(Booking::getProjectId));

        // Step 3: Adjust the start and end times of all bookings
        LocalDateTime currentStart = earliestStart;
        for (Booking booking : dayBookings) {
            long duration = durationInMinutes(booking);
            booking.setStart(currentStart);
            booking.setEnd(currentStart.plusMinutes(duration));
            currentStart = booking.getEnd();
        }

        return dayBookings;
    }

    private static long durationInMinutes(Booking booking) {
        return java.time.Duration.between(booking.getStart(), booking.getEnd()).toMinutes();
    }
}
