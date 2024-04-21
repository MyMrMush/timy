package de.dhbw.ase.timy.plugins.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.timy.domain.entities.EntityExample;
@Repository
public interface springDataForBridge extends JpaRepository<EntityExample, String> {

}
