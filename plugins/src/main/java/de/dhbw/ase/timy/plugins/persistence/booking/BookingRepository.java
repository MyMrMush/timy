package de.dhbw.ase.timy.plugins.persistence.booking;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import de.dhbw.ase.timy.domain.entities.booking.booking.BookingBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookingRepository implements BookingBridgeRepository {

    @Autowired
    BookingSpringRepository bookingSpringRepository;

    @Override
    public void save(Booking booking) {
        this.bookingSpringRepository.save(booking);
    }

    @Override
    public Optional<Booking> findById(int id) {
        return this.bookingSpringRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        this.bookingSpringRepository.deleteById(id);
    }
}
