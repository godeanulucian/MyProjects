package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.PharmacyInventoryDTO;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.entities.PharmacyInventory;
import lucian.ehealth.entities.Provider;
import lucian.ehealth.repositories.PharmacyInventoryRepository;
import lucian.ehealth.repositories.ProviderRepository;
import lucian.ehealth.validators.PharmacyInventoryValidator;
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
    @Autowired
    PharmacyInventoryValidator pharmacyInventoryValidator;
    @Autowired
    ProviderRepository providerRepository;

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
    public ResponseEntity<?> addToInventory(PharmacyInventoryDTO pharmacyInventoryDTO) {
        if (pharmacyInventoryValidator.validatePharmacyInventory(pharmacyInventoryDTO)) {
            PharmacyInventory pharmacyInventory = new PharmacyInventory(pharmacyInventoryDTO);
            PharmacyInventoryDTO response = new PharmacyInventoryDTO(pharmacyInventoryRepository.save(pharmacyInventory));

            Provider provider = providerRepository.findByFullName(pharmacyInventoryDTO.getProviderFullName());
            updateProvider(provider, true);

            System.out.println("\nItem with ID: " + response.getItemID() + " was created");
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Item was not created");
        }
    }

    // READ
    public ResponseEntity<?> getItem(String batchNumber) {
        PharmacyInventory pharmacyInventory = pharmacyInventoryRepository.findByBatchNumber(batchNumber);
        if (pharmacyInventory!=null) {
            PharmacyInventoryDTO response = new PharmacyInventoryDTO(pharmacyInventory);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Item not found");
        }
    }

    // UPDATE
    public ResponseEntity<?> updateInventory(PharmacyInventoryDTO pharmacyInventoryDTO, String batchNumber) {
        PharmacyInventory pharmacyInventory = pharmacyInventoryRepository.findByBatchNumber(batchNumber);
        if (pharmacyInventory!=null && pharmacyInventoryValidator.validateUpdatePharmacyInventory(pharmacyInventoryDTO)) {
            // pharmacyInventory.setItemName(pharmacyInventoryDTO.getItemName());
            // pharmacyInventory.setProviderFullName(pharmacyInventoryDTO.getProviderFullName());
            // pharmacyInventory.setCategory(pharmacyInventoryDTO.getCategory());
            pharmacyInventory.setQuantity(pharmacyInventoryDTO.getQuantity());
            pharmacyInventory.setUnitPrice(pharmacyInventoryDTO.getUnitPrice());
            // pharmacyInventory.setManufacturer();
            // pharmacyInventory.setExpirationDate();
            // pharmacyInventory.setBatchNumber();
            pharmacyInventory.setStockLevel(pharmacyInventoryDTO.getStockLevel());
            pharmacyInventory.setStorageConditions(pharmacyInventoryDTO.getStorageConditions());
            pharmacyInventory.setNotes(pharmacyInventoryDTO.getNotes());

            PharmacyInventoryDTO response = new PharmacyInventoryDTO(pharmacyInventoryRepository.save(pharmacyInventory));
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Item not found or update error");
        }
    }

    // DELETE
    public ResponseEntity<?> deleteFromInventory(String batchNumber) {
        PharmacyInventory pharmacyInventory = pharmacyInventoryRepository.findByBatchNumber(batchNumber);
        String providerName = pharmacyInventory.getProviderFullName();
        if (pharmacyInventory!=null) {
            pharmacyInventoryRepository.delete(pharmacyInventory);
            PharmacyInventoryDTO response = new PharmacyInventoryDTO();
            response.setReturnCode("Item deleted");

            Provider provider = providerRepository.findByFullName(providerName);
            updateProvider(provider, false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Item not found");
        }
    }

    public void updateProvider(Provider provider, boolean checker) {
        provider.setHasPharmacyInventory(checker);
    }

}
