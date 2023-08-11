package lucian.ehealth.services;

import lucian.ehealth.dto.PharmacyInventoryDTO;
import lucian.ehealth.repositories.PharmacyInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PharmacyInventoryService {

    @Autowired
    PharmacyInventoryDTO pharmacyInventoryDTO;
    @Autowired
    PharmacyInventoryRepository pharmacyInventoryRepository;

    public ResponseEntity<?> getAllInventory() {
        System.out.println("\n--- Pharmacy Inventory List ---\n");
        return new ResponseEntity<>(pharmacyInventoryRepository.findAll(), HttpStatus.OK);
    }

}
