package de.dhbw.ase.timy.domain.entities.category.category;

import de.dhbw.ase.timy.domain.entities.category.project.Project;
import de.dhbw.ase.timy.domain.values.Color;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private Color color;

    @OneToMany(mappedBy = "categoryId")
    private List<Project> projects;

    public Category(String name, String description, Color color) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.projects = new ArrayList<>();
    }

    public Category() {
        this.projects = new ArrayList<>();
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

    public List<Project> getProjects() {
        return projects;
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

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addProject(String name, String description, Color color, boolean internal, boolean active) {
        projects.add(
                new Project(name, description, color, internal, active)
        );
    }
}
