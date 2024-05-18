package de.dhbw.ase.timy.domain.entities.category.project;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectBridgeRepository {
    void save(Project project);
    Optional<Project> findById(int id);
    List<Project> findAll();
    void deleteById(int id);
}
