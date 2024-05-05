package de.dhbw.ase.timy.plugins.persistence.booking;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSpringRepository extends JpaRepository<Booking, Integer> {

}
