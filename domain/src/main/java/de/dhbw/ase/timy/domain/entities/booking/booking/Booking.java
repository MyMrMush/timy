package de.dhbw.ase.timy.domain.entities.booking.booking;

import de.dhbw.ase.timy.domain.services.DateTimeValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private int id;
    private int categoryId;
    private int projectId;
    private String title;
    private String description;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Booking(int categoryId, int projectId, String title, String description, LocalDateTime start, LocalDateTime end) {
        this.categoryId = categoryId;
        this.projectId = projectId;
        this.title = Objects.requireNonNull(title, "The title must not be null.");
        this.description = Objects.requireNonNull(description, "The description must not be null.");
        if  (start == null || end == null || !DateTimeValidator.isEndAfterStart(start, end)) {
            throw new IllegalArgumentException("The end date must be after the start date and both must be valid.");
        }
        this.start = start;
        this.end = end;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public Booking(int id, int categoryId, int projectId, String title, String description, LocalDateTime start, LocalDateTime end) {
        this(categoryId, projectId, title, description, start, end);
        this.id = id;
    }

    protected Booking() {
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getProjectId() {
        return projectId;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setId(int id) {
        this.id = id;
        this.updated = LocalDateTime.now();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        this.updated = LocalDateTime.now();
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
        this.updated = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
        this.updated = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.updated = LocalDateTime.now();
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
        this.updated = LocalDateTime.now();
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
        this.updated = LocalDateTime.now();
    }
}