package de.dhbw.ase.timy.domain.entities.category.project;

import org.springframework.stereotype.Repository;

@Repository
public interface ProjectBridgeRepository {
    void save(Project project);
    void findById(int id);
    void deleteById(int id);
}
