package de.dhbw.ase.timy.domain.entities.booking.booking;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingBridgeRepository {
    void save(Booking booking);
    Optional<Booking> findById(int id);
    void deleteById(int id);
}
