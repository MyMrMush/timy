package de.dhbw.ase.timy.domain.entities.booking.booking;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingBridgeRepository {
    void save(Booking booking);
    Optional<Booking> findById(int id);
    List<Booking> findAll();
    List<Booking> findAllByStartBetween(LocalDateTime start, LocalDateTime end);
    List<Booking> findAllByStartBetweenAndCategoryIdInAndProjectIdIn(LocalDateTime localDateTime, LocalDateTime localDateTime1, int[] categoryIds, int[] projectIds);
    List<Booking> findAllByStartBetweenAndCategoryIdIn(LocalDateTime localDateTime, LocalDateTime localDateTime1, int[] categoryIds);
    void deleteById(int id);
}
