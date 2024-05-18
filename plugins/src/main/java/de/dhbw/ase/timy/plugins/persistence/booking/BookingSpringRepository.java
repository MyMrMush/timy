package de.dhbw.ase.timy.plugins.persistence.booking;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingSpringRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByStartBetween(LocalDateTime start, LocalDateTime end);
    List<Booking> findAllByStartBetweenAndCategoryIdInAndProjectIdIn(LocalDateTime localDateTime, LocalDateTime localDateTime1, List<Integer> categoryIds, List<Integer> projectIds);
    List<Booking> findAllByStartBetweenAndCategoryIdIn(LocalDateTime localDateTime, LocalDateTime localDateTime1, List<Integer> collect);
}
