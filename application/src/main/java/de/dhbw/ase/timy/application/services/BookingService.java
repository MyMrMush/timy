package de.dhbw.ase.timy.application.services;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import de.dhbw.ase.timy.domain.entities.booking.booking.BookingBridgeRepository;
import de.dhbw.ase.timy.domain.services.BookingOptimiser;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    private final BookingBridgeRepository bookingRepository;
    private final CategoryService categoryService;

    @Autowired
    public BookingService(BookingBridgeRepository bookingRepository, CategoryService categoryService) {
        this.bookingRepository = bookingRepository;
        this.categoryService = categoryService;
    }

    public void createBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).ifPresent(b -> {
            throw new EntityExistsException("Booking with id " + booking.getId() + " already exists");
        });
        try {
            categoryService.findProjectById(booking.getProjectId(), booking.getCategoryId());
            bookingRepository.save(booking);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Project with id " + booking.getProjectId() + " does not exist in category with id " + booking.getCategoryId());
        }
    }

    public Booking findBookingById(int id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id " + id + " does not exist"));
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> findByStartBetween(LocalDate start, LocalDate end) {
        return bookingRepository.findAllByStartBetween(start.atStartOfDay(), end.atStartOfDay().plusDays(1).minusMinutes(1));
    }

    public List<Booking> findByStartBetweenAndCategoryInAndProjectIn(LocalDate start, LocalDate end, int[] categoryIds, int[] projectIds) {
        return bookingRepository.findAllByStartBetweenAndCategoryIdInAndProjectIdIn(start.atStartOfDay(), end.atStartOfDay().plusDays(1).minusMinutes(1), categoryIds, projectIds);
    }

    public List<Booking> findByStartBetweenAndCategoryIn(LocalDate start, LocalDate end, int[] categoryIds) {
        return bookingRepository.findAllByStartBetweenAndCategoryIdIn(start.atStartOfDay(), end.atStartOfDay().plusDays(1).minusMinutes(1), categoryIds);
    }

    public void updateBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).orElseThrow(() -> new EntityNotFoundException("Booking with id " + booking.getId() + " does not exist"));
        bookingRepository.save(booking);
    }

    public void deleteBookingById(int id) {
        bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id " + id + " does not exist"));
        bookingRepository.deleteById(id);
    }

    public List<Booking> optimiseBookings(LocalDate start, LocalDate end) {
        List<Booking> bookings = bookingRepository.findAllByStartBetween(start.atStartOfDay(), end.atStartOfDay().plusDays(1).minusMinutes(1));
        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("No bookings found between " + start + " and " + end);
        }
        bookings = BookingOptimiser.optimiseBookings(bookings);
        bookings.forEach(bookingRepository::save);
        return  bookings;
    }
}
