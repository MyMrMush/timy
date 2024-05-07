package de.dhbw.ase.timy.adapters.representations;

public class ProjectDTO {
    private int id;
    private String name;
    private String description;
    private String color;
    private boolean internal;
    private boolean active;
    private int categoryId;

    public ProjectDTO(int id, String name, String description, String color, boolean internal, boolean active, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.internal = internal;
        this.active = active;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
