package de.dhbw.ase.timy.plugins.persistence.booking;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import de.dhbw.ase.timy.domain.entities.booking.booking.BookingBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookingRepository implements BookingBridgeRepository {

    @Autowired
    BookingSpringRepository bookingSpringRepository;

    @Override
    public void save(Booking booking) {
        this.bookingSpringRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingSpringRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(int id) {
        return this.bookingSpringRepository.findById(id);
    }

    @Override
    public List<Booking> findAllByStartBetween(LocalDateTime start, LocalDateTime end) {
        return this.bookingSpringRepository.findAllByStartBetween(start, end);
    }

    @Override
    public List<Booking> findAllByStartBetweenAndCategoryIdInAndProjectIdIn(LocalDateTime localDateTime, LocalDateTime localDateTime1, int[] categoryIds, int[] projectIds) {
        return this.bookingSpringRepository.findAllByStartBetweenAndCategoryIdInAndProjectIdIn(localDateTime, localDateTime1, Arrays.stream(categoryIds).boxed().collect(Collectors.toList()), Arrays.stream(projectIds).boxed().collect(Collectors.toList()));
    }

    @Override
    public List<Booking> findAllByStartBetweenAndCategoryIdIn(LocalDateTime localDateTime, LocalDateTime localDateTime1, int[] categoryIds) {
        return this.bookingSpringRepository.findAllByStartBetweenAndCategoryIdIn(localDateTime, localDateTime1, Arrays.stream(categoryIds).boxed().collect(Collectors.toList()));
    }

    @Override
    public void deleteById(int id) {
        this.bookingSpringRepository.deleteById(id);
    }
}
