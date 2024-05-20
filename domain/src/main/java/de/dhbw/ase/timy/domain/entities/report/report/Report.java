package de.dhbw.ase.timy.domain.entities.report.report;

import de.dhbw.ase.timy.domain.entities.report.bookingrep.BookingReportRepresentation;
import de.dhbw.ase.timy.domain.templates.Descriptive;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Entity
public class Report implements Descriptive, Iterable<BookingReportRepresentation> {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    @Column(name = "start_date")
    private LocalDate start;
    @Column(name = "end_date")
    private LocalDate end;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookingReportRepresentation> bookings;
    private Long totalDuration;
    private LocalDateTime created;

    public Report(String name, String description, LocalDate start, LocalDate end, List<BookingReportRepresentation> bookings, Long totalDuration, LocalDateTime created) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.bookings = bookings;
        this.totalDuration = totalDuration;
        this.created = created;
    }

    public Report() {
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

    public List<BookingReportRepresentation> getBookings() {
        return bookings;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Iterator<BookingReportRepresentation> iterator() {
        return bookings.iterator();
    }
}
