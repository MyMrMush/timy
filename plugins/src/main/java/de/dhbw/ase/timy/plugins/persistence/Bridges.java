package de.dhbw.ase.timy.plugins.persistence;

import de.dhbw.ase.timy.domain.repositories.BridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


//Muss das jeweilige Domain Repository implementieren
@Repository
public class Bridges  implements BridgeRepository {

	@Autowired
	springDataForBridge springDataForBridge;
	
	@Override
	public void deleteById(Long id) {
		// implementieren der "Bridge"
		//z.B. springDataForBridge.deleteById(id);
		
	}



	
	/**
	 * In dem package landen die ganzen Bridges welche benutzt werden zur kommunikation
	 * zwischen den schichten.
	 */
}
