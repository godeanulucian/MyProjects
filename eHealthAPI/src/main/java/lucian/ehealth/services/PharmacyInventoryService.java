package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.PharmacyInventoryDTO;
import lucian.ehealth.entities.PharmacyInventory;
import lucian.ehealth.repositories.PharmacyInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PharmacyInventoryService {

    @Autowired
    PharmacyInventoryDTO pharmacyInventoryDTO;
    @Autowired
    PharmacyInventoryRepository pharmacyInventoryRepository;

    // BAD REQUEST HANDLER
    public ResponseEntity<?> handleBadRequest(String returnCode) {
        PharmacyInventoryDTO response = new PharmacyInventoryDTO();
        response.setReturnCode(returnCode);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // READ ALL
    public ResponseEntity<?> getAllInventory() {
        List<PharmacyInventory> items = new ArrayList<>();
        pharmacyInventoryRepository.findAll().forEach(items::add);
        if (!items.isEmpty()) {
            return new ResponseEntity<>(items, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("No items found");
        }
    }

    // CREATE


}
