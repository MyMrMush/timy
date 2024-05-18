package de.dhbw.ase.timy.adapters.representations;

import java.time.LocalDateTime;

public class BookingReportRepresentationDTO {
    private final int id;
    //private final int reportId;
    private final int bookingId;
    private final int categoryId;
    private final String categoryName;
    private final int projectId;
    private final String projectName;
    private final String title;
    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final long bookingDuration;
    private final LocalDateTime bookingCreated;
    private final LocalDateTime bookingUpdated;

    public BookingReportRepresentationDTO(int id, /*int reportId,*/ int bookingId, int categoryId, String categoryName, int projectId, String projectName, String title, String description, LocalDateTime start, LocalDateTime end, long bookingDuration, LocalDateTime bookingCreated, LocalDateTime bookingUpdated) {
        this.id = id;
        //this.reportId = reportId;
        this.bookingId = bookingId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.bookingDuration = bookingDuration;
        this.bookingCreated = bookingCreated;
        this.bookingUpdated = bookingUpdated;
    }

    public int getId() {
        return id;
    }

    /*public int getReportId() {
        return reportId;
    }*/

    public int getBookingId() {
        return bookingId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public long getBookingDuration() {
        return bookingDuration;
    }

    public LocalDateTime getBookingCreated() {
        return bookingCreated;
    }

    public LocalDateTime getBookingUpdated() {
        return bookingUpdated;
    }
}
