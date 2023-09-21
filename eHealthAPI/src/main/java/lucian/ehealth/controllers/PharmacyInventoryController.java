package lucian.ehealth.controllers;

import lucian.ehealth.services.PharmacyInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
