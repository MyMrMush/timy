package de.dhbw.ase.timy.adapters.representations;

import java.time.LocalDateTime;

public class BookingDTO {
    private int id;
    private int categoryId;
    private int projectId;
    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public BookingDTO(int id, int categoryId, int projectId, String title, String description, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.categoryId = categoryId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}

