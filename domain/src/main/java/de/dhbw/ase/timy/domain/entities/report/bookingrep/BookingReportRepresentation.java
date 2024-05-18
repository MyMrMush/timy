package de.dhbw.ase.timy.domain.entities.report.bookingrep;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BookingReportRepresentation {
    @Id
    @GeneratedValue
    private int id;
    //private int reportId;
    private int bookingId;
    private int categoryId;
    private String categoryName;
    private int projectId;
    private String projectName;
    private String title;
    private String description;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    private long bookingDuration;
    private LocalDateTime bookingCreated;
    private LocalDateTime bookingUpdated;
    private LocalDateTime created;
    private LocalDateTime updated;

    public BookingReportRepresentation() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    /*public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
        this.updated = LocalDateTime.now();
    }*/

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
        this.updated = LocalDateTime.now();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        this.updated = LocalDateTime.now();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        this.updated = LocalDateTime.now();
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
        this.updated = LocalDateTime.now();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        this.updated = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updated = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updated = LocalDateTime.now();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
        this.updated = LocalDateTime.now();
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public long getBookingDuration() {
        return bookingDuration;
    }

    public void setBookingDuration(long bookingDuration) {
        this.bookingDuration = bookingDuration;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
        this.updated = LocalDateTime.now();
    }

    public LocalDateTime getBookingCreated() {
        return bookingCreated;
    }

    public void setBookingCreated(LocalDateTime bookingCreated) {
        this.bookingCreated = bookingCreated;
        this.updated = LocalDateTime.now();
    }

    public LocalDateTime getBookingUpdated() {
        return bookingUpdated;
    }

    public void setBookingUpdated(LocalDateTime bookingUpdated) {
        this.bookingUpdated = bookingUpdated;
        this.updated = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
}
