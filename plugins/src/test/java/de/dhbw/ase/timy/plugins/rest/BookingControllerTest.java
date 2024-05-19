package de.dhbw.ase.timy.plugins.rest;

import de.dhbw.ase.timy.adapters.representations.BookingDTO;
import de.dhbw.ase.timy.adapters.representations.DateRangeDTO;
import de.dhbw.ase.timy.adapters.representations.mappers.booking.BookingToDTOMapper;
import de.dhbw.ase.timy.adapters.representations.mappers.booking.DTOToBookingMapper;
import de.dhbw.ase.timy.application.services.BookingService;
import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Mock
    private BookingToDTOMapper bookingToDTOMapper;

    @Mock
    private DTOToBookingMapper dtoToBookingMapper;

    @Test
    void getBookingValid() {
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(bookingService.findBookingById(1)).thenReturn(mockBooking);
        when(bookingToDTOMapper.apply(mockBooking)).thenReturn(mockBookingDTO);

        ResponseEntity<BookingDTO> response = bookingController.getBooking(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBookingDTO, response.getBody());
    }

    @Test
    void getBookingNotFound() {
        when(bookingService.findBookingById(1)).thenThrow(new EntityNotFoundException("Booking not found"));

        ResponseEntity<BookingDTO> response = bookingController.getBooking(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getBookingsEmpty() {
        when(bookingService.findAllBookings()).thenReturn(List.of());

        ResponseEntity<List<BookingDTO>> response = bookingController.getBookings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void getBookingsValid(){
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(bookingService.findAllBookings()).thenReturn(List.of(mockBooking));
        when(bookingToDTOMapper.apply(mockBooking)).thenReturn(mockBookingDTO);

        ResponseEntity<List<BookingDTO>> response = bookingController.getBookings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(mockBookingDTO, response.getBody().get(0));
    }

    @Test
    void createBookingValid() {
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(dtoToBookingMapper.apply(mockBookingDTO)).thenReturn(mockBooking);
        when(bookingToDTOMapper.apply(mockBooking)).thenReturn(mockBookingDTO);

        ResponseEntity<BookingDTO> response = bookingController.createBooking(mockBookingDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockBookingDTO, response.getBody());
    }

    @Test
    void createBookingNotFound() {
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(dtoToBookingMapper.apply(mockBookingDTO)).thenReturn(mockBooking);
        doThrow(new EntityNotFoundException("Project with id X does not exist in category with id Y")).when(bookingService).createBooking(mockBooking);

        ResponseEntity<BookingDTO> response = bookingController.createBooking(mockBookingDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createBookingInvalidInput() {
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(dtoToBookingMapper.apply(mockBookingDTO)).thenReturn(mockBooking);
        doThrow(new RuntimeException("Invalid input")).when(bookingService).createBooking(mockBooking);

        ResponseEntity<BookingDTO> response = bookingController.createBooking(mockBookingDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateBookingValid() {
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(dtoToBookingMapper.apply(mockBookingDTO)).thenReturn(mockBooking);
        when(bookingToDTOMapper.apply(mockBooking)).thenReturn(mockBookingDTO);

        ResponseEntity<BookingDTO> response = bookingController.updateBooking(mockBookingDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBookingDTO, response.getBody());
    }

    @Test
    void updateBookingNotFound() {
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(dtoToBookingMapper.apply(mockBookingDTO)).thenReturn(mockBooking);
        doThrow(new EntityNotFoundException("Booking not found")).when(bookingService).updateBooking(mockBooking);

        ResponseEntity<BookingDTO> response = bookingController.updateBooking(mockBookingDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateBookingInvalidInput() {
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(dtoToBookingMapper.apply(mockBookingDTO)).thenReturn(mockBooking);
        doThrow(new RuntimeException("Invalid input")).when(bookingService).updateBooking(mockBooking);

        ResponseEntity<BookingDTO> response = bookingController.updateBooking(mockBookingDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteBookingValid() {
        int id = 1;
        doNothing().when(bookingService).deleteBookingById(id);

        ResponseEntity<Void> response = bookingController.deleteBooking(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull( response.getBody());
    }

    @Test
    void deleteBookingNotFound() {
        int id = 1;
        doThrow(new EntityNotFoundException("Booking not found")).when(bookingService).deleteBookingById(id);

        ResponseEntity<Void> response = bookingController.deleteBooking(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull( response.getBody());
    }

    @Test
    void optimiseBookingsValid() {
        DateRangeDTO mockDateRangeDTO = new DateRangeDTO(LocalDate.parse("2021-06-01"), LocalDate.parse("2021-06-01"));
        Booking mockBooking = new Booking(1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        BookingDTO mockBookingDTO = new BookingDTO(1, 1, 1, "Test Booking 1", "Test Booking 1", LocalDateTime.parse("2021-06-01T12:00:00"), LocalDateTime.parse("2021-06-01T12:15:00"));
        when(bookingService.optimiseBookings(any(), any())).thenReturn(List.of(mockBooking));
        when(bookingToDTOMapper.apply(mockBooking)).thenReturn(mockBookingDTO);

        ResponseEntity<List<BookingDTO>> response = bookingController.optimiseBookings(mockDateRangeDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(mockBookingDTO, response.getBody().get(0));
    }

    @Test
    void optimiseBookingsNoBookingsFound() {
        DateRangeDTO mockDateRangeDTO = new DateRangeDTO(LocalDate.parse("2021-06-01"), LocalDate.parse("2021-06-01"));
        when(bookingService.optimiseBookings(any(), any())).thenThrow(new EntityNotFoundException("No bookings found"));

        ResponseEntity<List<BookingDTO>> response = bookingController.optimiseBookings(mockDateRangeDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void optimiseBookingsInvalidInput() {
        DateRangeDTO mockDateRangeDTO = new DateRangeDTO(LocalDate.parse("2021-06-02"), LocalDate.parse("2021-06-01"));
        when(bookingService.optimiseBookings(any(), any())).thenThrow(new RuntimeException("Invalid input"));

        ResponseEntity<List<BookingDTO>> response = bookingController.optimiseBookings(mockDateRangeDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}