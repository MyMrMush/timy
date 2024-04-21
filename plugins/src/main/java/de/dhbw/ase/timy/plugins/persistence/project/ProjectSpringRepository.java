package de.dhbw.ase.timy.plugins.persistence.project;

import de.dhbw.ase.timy.domain.entities.category.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSpringRepository extends JpaRepository<Project, Integer> {

}
