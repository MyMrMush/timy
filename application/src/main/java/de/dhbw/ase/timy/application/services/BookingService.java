package de.dhbw.ase.timy.application.services;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import de.dhbw.ase.timy.domain.entities.booking.booking.BookingBridgeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingBridgeRepository bookingRepository;

    @Autowired
    public BookingService(BookingBridgeRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void createBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).ifPresent(b -> {
            throw new EntityExistsException("Booking with id " + booking.getId() + " already exists");
        });
        bookingRepository.save(booking);
    }

    public Booking findBookingById(int id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id " + id + " does not exist"));
    }

    public void updateBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).orElseThrow(() -> new EntityNotFoundException("Booking with id " + booking.getId() + " does not exist"));
        bookingRepository.save(booking);
    }

    public void deleteBookingById(int id) {
        bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id " + id + " does not exist"));
        bookingRepository.deleteById(id);
    }
}
