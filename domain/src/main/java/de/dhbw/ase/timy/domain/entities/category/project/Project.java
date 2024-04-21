package de.dhbw.ase.timy.domain.entities.category.project;

import de.dhbw.ase.timy.domain.values.Color;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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

    private int categoryId;

    public Project(String name, String description, Color color, boolean internal, boolean active) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.internal = internal;
        this.active = active;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
