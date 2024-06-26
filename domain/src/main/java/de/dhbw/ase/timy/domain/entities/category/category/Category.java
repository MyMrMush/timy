package de.dhbw.ase.timy.domain.entities.category.category;

import de.dhbw.ase.timy.domain.entities.category.project.Project;
import de.dhbw.ase.timy.domain.templates.Descriptive;
import de.dhbw.ase.timy.domain.templates.Timestamped;
import de.dhbw.ase.timy.domain.values.Color;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category implements Descriptive, Timestamped {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private Color color;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL)
    private List<Project> projects;

    public Category(String name, String description, Color color) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.projects = new ArrayList<>();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public Category() {
        this.projects = new ArrayList<>();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public Category(int id, String name, String description, Color color) {
        this(name, description, color);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.updated = LocalDateTime.now();
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
        this.updated = LocalDateTime.now();
    }

    public void setColor(Color color) {
        this.color = color;
        this.updated = LocalDateTime.now();
    }

    public void addProject(Project project) {
        projects.add(project);
        this.updated = LocalDateTime.now();
    }

    public void addProject(String name, String description, Color color, boolean internal, boolean active) {
        projects.add(
                new Project(name, description, color, internal, active)
        );
        this.updated = LocalDateTime.now();
    }

    public void setId(int id) {
        this.id = id;
        this.updated = LocalDateTime.now();
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        this.updated = LocalDateTime.now();
    }

    @Override
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
