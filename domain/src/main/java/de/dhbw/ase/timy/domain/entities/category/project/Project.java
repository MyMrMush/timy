package de.dhbw.ase.timy.domain.entities.category.project;

import de.dhbw.ase.timy.domain.values.Color;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private Color color;
    private boolean internal;
    private boolean active;
    private LocalDateTime created;
    private LocalDateTime updated;

    private int categoryId;

    public Project(String name, String description, Color color, boolean internal, boolean active) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.internal = internal;
        this.active = active;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public Project(int id, String name, String description, Color color, boolean internal, boolean active, int categoryId) {
        this(name, description, color, internal, active);
        this.id = id;
        this.categoryId = categoryId;
    }

    public Project() {

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

    public Color getColor() {
        return color;
    }

    public boolean isInternal() {
        return internal;
    }

    public boolean isActive() {
        return active;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
        this.updated = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
        this.updated = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.updated = LocalDateTime.now();
    }

    public void setColor(Color color) {
        this.color = color;
        this.updated = LocalDateTime.now();
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
        this.updated = LocalDateTime.now();
    }

    public void setActive(boolean active) {
        this.active = active;
        this.updated = LocalDateTime.now();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        this.updated = LocalDateTime.now();
    }
}
