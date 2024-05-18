package de.dhbw.ase.timy.plugins.rest;

import de.dhbw.ase.timy.adapters.representations.BookingDTO;
import de.dhbw.ase.timy.adapters.representations.DateRangeDTO;
import de.dhbw.ase.timy.adapters.representations.mappers.booking.BookingToDTOMapper;
import de.dhbw.ase.timy.adapters.representations.mappers.booking.DTOToBookingMapper;
import de.dhbw.ase.timy.application.services.BookingService;
import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Booking")
@RequestMapping("/booking")
public class BookingController {

	private final BookingService bookingService;
	private final BookingToDTOMapper bookingToDTOMapper;
	private final DTOToBookingMapper dtoToBookingMapper;

	@Autowired
	public BookingController(BookingService bookingService, BookingToDTOMapper bookingToDTOMapper, DTOToBookingMapper dtoToBookingMapper) {
		this.bookingService = bookingService;
		this.bookingToDTOMapper = bookingToDTOMapper;
		this.dtoToBookingMapper = dtoToBookingMapper;
	}

	@GetMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved Booking"),
			@ApiResponse(responseCode = "404", description = "Booking not found")
	})
	public ResponseEntity<BookingDTO> getBooking(@PathVariable int id) {
		try{
			Booking booking = bookingService.findBookingById(id);
			return ResponseEntity.ok(bookingToDTOMapper.apply(booking));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("s/")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved all Bookings")
	})
	public ResponseEntity<List<BookingDTO>> getBookings() {
		List<Booking> bookings = bookingService.findAllBookings();
		List<BookingDTO> bookingDTOs = bookings.stream().map(bookingToDTOMapper).toList();
		return ResponseEntity.ok(bookingDTOs);
	}

	@PostMapping("/")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Booking created"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
		try{
			Booking booking = dtoToBookingMapper.apply(bookingDTO);
			bookingService.createBooking(booking);
			return ResponseEntity.status(HttpStatus.CREATED).body(bookingToDTOMapper.apply(booking));
		} catch (EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		} catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Booking updated"),
			@ApiResponse(responseCode = "404", description = "Booking not found"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO) {
		try{
			Booking booking = dtoToBookingMapper.apply(bookingDTO);
			bookingService.updateBooking(booking);
			return ResponseEntity.ok(bookingToDTOMapper.apply(booking));
		} catch (EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		} catch (Exception e ){
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Booking successfully  deleted"),
			@ApiResponse(responseCode = "404", description = "Booking not found")
	})
	public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
		try{
			bookingService.deleteBookingById(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/optimise")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Bookings optimised"),
			@ApiResponse(responseCode = "404", description = "No bookings found"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	public ResponseEntity<List<BookingDTO>> optimiseBookings(@RequestBody DateRangeDTO timeRange){
		try{
			LocalDate start = timeRange.getStart();
			LocalDate end = timeRange.getEnd();
			List<Booking> optimisedBookings = bookingService.optimiseBookings(start, end);
			List<BookingDTO> optimisedBookingsDTOs = optimisedBookings.stream().map(bookingToDTOMapper).toList();
			return ResponseEntity.ok(optimisedBookingsDTOs);
		} catch (EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		} catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
}
