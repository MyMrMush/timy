package de.dhbw.ase.timy.domain.services;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingOptimiserTest {

    /**
     * Tests the optimiseBookings method of the BookingOptimiser class.
     * <p>
     * This test checks if the method correctly optimises the bookings for a single day.
     * The bookings are created with different start and end times, and the method is expected to sort them
     * and adjust their start and end times such that they do not overlap and are in chronological order.
     * The test then checks the size of the returned list and the start and end times of the first and last bookings in the returned list.
     */
    @Test
    void optimiseBookingsSimpleOneDay() {
        Booking booking   = new Booking(1, 1,  "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking booking2 = new Booking(1, 1,  "Test Booking 2", "Test Booking 2", LocalDateTime.parse("2021-06-01T12:15:00"), LocalDateTime.parse("2021-06-01T12:30:00"));
        Booking booking3 = new Booking(1, 1,  "Test Booking 3", "Test Booking 3", LocalDateTime.parse("2021-06-01T13:30:00"), LocalDateTime.parse("2021-06-01T13:45:00"));
        Booking booking4 = new Booking(1, 1,  "Test Booking 4", "Test Booking 4", LocalDateTime.parse("2021-06-01T08:00:00"), LocalDateTime.parse("2021-06-01T08:15:00"));
        List<Booking> bookings = List.of(booking, booking2, booking3, booking4);
        List<Booking> optimisedBookings;
        optimisedBookings = BookingOptimiser.optimiseBookings(bookings);

        assertEquals(4, optimisedBookings.size());
        assertEquals(LocalDateTime.parse("2021-06-01T08:00:00"), optimisedBookings.get(0).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T09:00:00"), optimisedBookings.get(3).getEnd());
    }

    /**
     * Tests the optimiseBookings method of the BookingOptimiser class.
     * <p>
     * This test checks if the method correctly optimises the bookings for a single day.
     * The bookings are created with different start and end times, and the method is expected to sort them
     * and adjust their start and end times such that they do not overlap and are in chronological order.
     * The test then checks the size of the returned list and the start and end times of the first and last bookings in the returned list.
     * Additionally, it verifies the category and project IDs of the bookings.
     */
    @Test
    void optimiseBookingsComplexOneDay() {
        Booking booking   = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking booking2 = new Booking(3, 4, "Test Booking 2", "Test Booking 2", LocalDateTime.parse("2021-06-01T12:15:00"), LocalDateTime.parse("2021-06-01T12:30:00"));
        Booking booking3 = new Booking(2, 3, "Test Booking 3", "Test Booking 3", LocalDateTime.parse("2021-06-01T13:30:00"), LocalDateTime.parse("2021-06-01T13:45:00"));
        Booking booking4 = new Booking(1, 2, "Test Booking 4", "Test Booking 4", LocalDateTime.parse("2021-06-01T08:00:00"), LocalDateTime.parse("2021-06-01T08:15:00"));
        List<Booking> bookings = List.of(booking, booking2, booking3, booking4);
        List<Booking> optimisedBookings = BookingOptimiser.optimiseBookings(bookings);

        assertEquals(4, optimisedBookings.size());
        assertEquals(LocalDateTime.parse("2021-06-01T08:00:00"), optimisedBookings.get(0).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T08:15:00"), optimisedBookings.get(0).getEnd());
        assertEquals(1, optimisedBookings.get(0).getCategoryId());
        assertEquals(1, optimisedBookings.get(0).getProjectId());

        assertEquals(LocalDateTime.parse("2021-06-01T08:15:00"), optimisedBookings.get(1).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T08:30:00"), optimisedBookings.get(1).getEnd());
        assertEquals(1, optimisedBookings.get(1).getCategoryId());
        assertEquals(2, optimisedBookings.get(1).getProjectId());

        assertEquals(LocalDateTime.parse("2021-06-01T08:30:00"), optimisedBookings.get(2).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T08:45:00"), optimisedBookings.get(2).getEnd());
        assertEquals(2, optimisedBookings.get(2).getCategoryId());
        assertEquals(3, optimisedBookings.get(2).getProjectId());

        assertEquals(LocalDateTime.parse("2021-06-01T08:45:00"), optimisedBookings.get(3).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T09:00:00"), optimisedBookings.get(3).getEnd());
        assertEquals(3, optimisedBookings.get(3).getCategoryId());
        assertEquals(4, optimisedBookings.get(3).getProjectId());
    }

    /**
     * Tests the optimiseBookings method of the BookingOptimiser class.
     * <p>
     * This test checks if the method correctly optimises the bookings for two days.
     * The bookings are created with different start and end times, and the method is expected to sort them
     * and adjust their start and end times such that they do not overlap and are in chronological order.
     * The test then checks the size of the returned list and the start and end times of the first and last bookings in the returned list.
     */
    @Test
    void optimiseBookingsSimpleTwoDays() {
        Booking booking   = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking booking2 = new Booking(1, 1, "Test Booking 2", "Test Booking 2", LocalDateTime.parse("2021-06-01T12:15:00"), LocalDateTime.parse("2021-06-01T12:30:00"));
        Booking booking3 = new Booking(1, 1, "Test Booking 3", "Test Booking 3", LocalDateTime.parse("2021-06-02T13:30:00"), LocalDateTime.parse("2021-06-02T13:45:00"));
        Booking booking4 = new Booking(1, 1, "Test Booking 4", "Test Booking 4", LocalDateTime.parse("2021-06-02T08:00:00"), LocalDateTime.parse("2021-06-02T08:15:00"));
        List<Booking> bookings = List.of(booking, booking2, booking3, booking4);
        List<Booking> optimisedBookings = BookingOptimiser.optimiseBookings(bookings);
        optimisedBookings.sort(Comparator.comparing(Booking::getStart));

        assertEquals(4, optimisedBookings.size());
        assertEquals(LocalDateTime.parse("2021-06-01T12:00:00"), optimisedBookings.get(0).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T12:30:00"), optimisedBookings.get(1).getEnd());
        assertEquals(LocalDateTime.parse("2021-06-02T08:00:00"), optimisedBookings.get(2).getStart());
        assertEquals(LocalDateTime.parse("2021-06-02T08:30:00"), optimisedBookings.get(3).getEnd());
    }

    /**
     * Tests the optimiseBookings method of the BookingOptimiser class.
     * <p>
     * This test checks if the method correctly optimises the bookings for two days.
     * The bookings are created with different start and end times, and the method is expected to sort them
     * and adjust their start and end times such that they do not overlap and are in chronological order.
     * The test then checks the size of the returned list and the start and end times of the first and last bookings in the returned list.
     * Additionally, it verifies the category and project IDs of the bookings.
     */
    @Test
    void optimiseBookingsComplexTwoDays() {
        Booking booking   = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking booking2 = new Booking(3, 4, "Test Booking 2", "Test Booking 2", LocalDateTime.parse("2021-06-01T12:15:00"), LocalDateTime.parse("2021-06-01T12:30:00"));
        Booking booking3 = new Booking(2, 3, "Test Booking 3", "Test Booking 3", LocalDateTime.parse("2021-06-02T13:30:00"), LocalDateTime.parse("2021-06-02T13:45:00"));
        Booking booking4 = new Booking(1, 2, "Test Booking 4", "Test Booking 4", LocalDateTime.parse("2021-06-02T08:00:00"), LocalDateTime.parse("2021-06-02T08:15:00"));
        List<Booking> bookings = List.of(booking, booking2, booking3, booking4);
        List<Booking> optimisedBookings = BookingOptimiser.optimiseBookings(bookings);
        optimisedBookings.sort(Comparator.comparing(Booking::getStart));

        assertEquals(4, optimisedBookings.size());
        assertEquals(LocalDateTime.parse("2021-06-01T12:00:00"), optimisedBookings.get(0).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T12:15:00"), optimisedBookings.get(0).getEnd());
        assertEquals(1, optimisedBookings.get(0).getCategoryId());

        assertEquals(LocalDateTime.parse("2021-06-01T12:15:00"), optimisedBookings.get(1).getStart());
        assertEquals(LocalDateTime.parse("2021-06-01T12:30:00"), optimisedBookings.get(1).getEnd());
        assertEquals(3, optimisedBookings.get(1).getCategoryId());

        assertEquals(LocalDateTime.parse("2021-06-02T08:00:00"), optimisedBookings.get(2).getStart());
        assertEquals(LocalDateTime.parse("2021-06-02T08:15:00"), optimisedBookings.get(2).getEnd());
        assertEquals(1, optimisedBookings.get(2).getCategoryId());

        assertEquals(LocalDateTime.parse("2021-06-02T08:15:00"), optimisedBookings.get(3).getStart());
        assertEquals(LocalDateTime.parse("2021-06-02T08:30:00"), optimisedBookings.get(3).getEnd());
        assertEquals(2, optimisedBookings.get(3).getCategoryId());
    }
}