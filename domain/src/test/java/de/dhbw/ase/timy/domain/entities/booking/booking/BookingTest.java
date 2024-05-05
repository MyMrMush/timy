package de.dhbw.ase.timy.domain.entities.booking.booking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingTest {

    @Test
    void testBookingConstructer(){
        // Test not null
        assertThrows(
                IllegalArgumentException.class,
                () -> new Booking(1, 1, "title", "description", null, null)
        );

        // Test dateTimes being valid (15m)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse("2024-01-01 12:13", formatter);
        LocalDateTime end = start.plusHours(1);
        assertThrows(
                IllegalArgumentException.class,
                () -> new Booking(1, 1, "title", "description",
                        start, end)
        );

        // Test end after start
        LocalDateTime start2 = LocalDateTime.parse("2024-01-01 12:00", formatter);
        LocalDateTime end2 = start2.minusHours(1);
        assertThrows(
                IllegalArgumentException.class,
                () -> new Booking(1, 1, "title", "description",
                        start2, end2)
        );

        // Test valid booking
        LocalDateTime start3 = LocalDateTime.parse("2024-01-01 12:00", formatter);
        LocalDateTime end3 = start3.plusHours(1);
        Booking booking = new Booking(1, 1, "title", "description",
                start3, end3);
    }
}