package lucian.ehealth.controllers;

import lucian.ehealth.dto.PharmacyInventoryDTO;
import lucian.ehealth.services.PharmacyInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/inventory")
public class PharmacyInventoryController {

    @Autowired
    PharmacyInventoryService pharmacyInventoryService;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllInventory() {
        return pharmacyInventoryService.getAllInventory();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addToInventory(@RequestBody PharmacyInventoryDTO pharmacyInventoryDTO) {
        return pharmacyInventoryService.addToInventory(pharmacyInventoryDTO);
    }

    // READ
    @GetMapping(path = "{batchNumber}")
    public ResponseEntity<?> getItem(@PathVariable String batchNumber) {
        return pharmacyInventoryService.getItem(batchNumber);
    }

    // UPDATE
    @PutMapping(path = "{batchNumber}")
    public ResponseEntity<?> updateItem(@RequestBody PharmacyInventoryDTO pharmacyInventoryDTO, @PathVariable String batchNumber) {
        return pharmacyInventoryService.updateInventory(pharmacyInventoryDTO, batchNumber);
    }

    // DELETE
    @DeleteMapping(path = "{batchNumber}")
    public ResponseEntity<?> deleteFromInventory(@PathVariable String batchNumber) {
        return pharmacyInventoryService.deleteFromInventory(batchNumber);
    }

}
