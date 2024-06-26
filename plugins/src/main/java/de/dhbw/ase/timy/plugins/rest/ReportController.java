package de.dhbw.ase.timy.plugins.rest;

import de.dhbw.ase.timy.adapters.representations.BookingReportRepresentationDTO;
import de.dhbw.ase.timy.adapters.representations.ReportDTO;
import de.dhbw.ase.timy.adapters.representations.ReportRequestDTO;
import de.dhbw.ase.timy.adapters.representations.mappers.bookingreportrep.BookingReportRepToDTOMapper;
import de.dhbw.ase.timy.adapters.representations.mappers.report.ReportToDTOMapper;
import de.dhbw.ase.timy.application.services.ReportService;
import de.dhbw.ase.timy.domain.entities.report.report.Report;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Report")
@RequestMapping("/report")
public class ReportController {
    private final ReportToDTOMapper reportToDTOMapper;
    private final ReportService reportService;
    private final BookingReportRepToDTOMapper bookingReportRepToDTOMapper;

    @Autowired
    public ReportController(ReportToDTOMapper reportToDTOMapper, ReportService reportService, BookingReportRepToDTOMapper bookingReportRepToDTOMapper) {
        this.reportToDTOMapper = reportToDTOMapper;
        this.reportService = reportService;
        this.bookingReportRepToDTOMapper = bookingReportRepToDTOMapper;
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Report"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<ReportDTO> getReport(@PathVariable int id) {
        try {
            Report report = reportService.findReportById(id);
            return ResponseEntity.ok(reportToDTOMapper.apply(report));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report created"),
            @ApiResponse(responseCode = "404", description = "No bookings found for the given time range"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportRequestDTO reportRequestDTO) {
        try {
            Report report;
            if(reportRequestDTO.isFiltered()) {
                report = reportService.createReport(reportRequestDTO.getName(), reportRequestDTO.getDescription(), reportRequestDTO.getStart(), reportRequestDTO.getEnd(), reportRequestDTO.getCategoryIds(), reportRequestDTO.getProjectIds());
            } else {
                report = reportService.createReport(reportRequestDTO.getName(), reportRequestDTO.getDescription(), reportRequestDTO.getStart(), reportRequestDTO.getEnd());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(reportToDTOMapper.apply(report));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{reportId}/bookings/{categoryId}/{projectId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved bookings"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<List<BookingReportRepresentationDTO>> getBookingsFromReportFiltered(@PathVariable int reportId, @PathVariable int categoryId, @PathVariable int projectId) {
        try {
            return ResponseEntity.ok(
                    reportService.getBookingFromReportFiltered(reportId, categoryId, projectId).stream()
                            .map(bookingReportRepToDTOMapper)
                            .toList()
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
