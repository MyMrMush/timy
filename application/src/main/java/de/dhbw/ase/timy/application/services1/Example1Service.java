package de.dhbw.ase.timy.application.services1;


import de.dhbw.ase.timy.domain.entities.category.category.CategoryBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Example1Service {

	@Autowired
    private CategoryBridgeRepository categoryRepository;
    
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
       
    }
}
