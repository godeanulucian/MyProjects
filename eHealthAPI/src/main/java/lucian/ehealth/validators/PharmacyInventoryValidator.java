package lucian.ehealth.validators;

import lucian.ehealth.dto.PharmacyInventoryDTO;
import lucian.ehealth.repositories.PharmacyInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PharmacyInventoryValidator {

    @Autowired
    PharmacyInventoryRepository pharmacyInventoryRepository;
    String batchNumberRegex = "^[A-Z]{2}\\d{6}$"; // two uppercase letters followed by six digits, it depends on pharmacy rules

    public boolean matcher(PharmacyInventoryDTO pharmacyInventoryDTO) {
        return pharmacyInventoryDTO!=null
                && pharmacyInventoryDTO.getItemName().length() <= 128
                && pharmacyInventoryDTO.getProviderFullName().matches("[a-zA-Z .-]{3,128}+")
                && pharmacyInventoryDTO.getCategory().length() <= 128
                && pharmacyInventoryDTO.getQuantity() >= 0
                && pharmacyInventoryDTO.getUnitPrice() >= 0
                && pharmacyInventoryDTO.getManufacturer().matches("[a-zA-Z .-]{3,128}+")
                && pharmacyInventoryDTO.getExpirationDate().isAfter(LocalDate.now())
                && pharmacyInventoryDTO.getBatchNumber().matches(batchNumberRegex)
                && pharmacyInventoryDTO.getStockLevel() >= 0
                && pharmacyInventoryDTO.getStorageConditions().length() <= 128
                && pharmacyInventoryDTO.getNotes().length() <= 128;
    }

    /*public boolean updateMatcher(PharmacyInventoryDTO pharmacyInventoryDTO) { so we can update custom fields, not the whole entity, but IDK if its worth it
        return pharmacyInventoryDTO!=null
                && (pharmacyInventoryDTO.getQuantity()==null || pharmacyInventoryDTO.getQuantity() >= 0)
                && (pharmacyInventoryDTO.getUnitPrice()==null || pharmacyInventoryDTO.getUnitPrice() >= 0)
                && (pharmacyInventoryDTO.getStockLevel()==null || pharmacyInventoryDTO.getStockLevel() >= 0)
                && (pharmacyInventoryDTO.getStorageConditions()==null || pharmacyInventoryDTO.getStorageConditions().length() <= 128)
                && (pharmacyInventoryDTO.getNotes()==null || pharmacyInventoryDTO.getNotes().length() <= 128);
    }*/

    public boolean validatePharmacyInventory(PharmacyInventoryDTO pharmacyInventoryDTO) {
        return matcher(pharmacyInventoryDTO)
                // unique batchNumber
                && pharmacyInventoryRepository.findByBatchNumber(pharmacyInventoryDTO.getBatchNumber())==null;
    }

    public boolean validateUpdatePharmacyInventory(PharmacyInventoryDTO pharmacyInventoryDTO) {
        return matcher(pharmacyInventoryDTO);
    }

    /*public boolean validateUpdatePharmacyInventory(PharmacyInventoryDTO pharmacyInventoryDTO) {
        return updateMatcher(pharmacyInventoryDTO);
    }*/

}
