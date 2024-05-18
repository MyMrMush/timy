package de.dhbw.ase.timy.adapters.representations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReportDTO {
    private final int id;
    private final String name;
    private final String description;
    private final LocalDate start;
    private final LocalDate end;
    private final List<BookingReportRepresentationDTO> bookings;
    private final Long totalDuration;
    private final LocalDateTime created;

    public ReportDTO(int id, String name, String description, LocalDate start, LocalDate end, List<BookingReportRepresentationDTO> bookings, Long totalDuration, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.bookings = bookings;
        this.totalDuration = totalDuration;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public List<BookingReportRepresentationDTO> getBookings() {
        return bookings;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
