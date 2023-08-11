package lucian.ehealth.controllers;

import lucian.ehealth.services.PharmacyInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PharmacyInventoryController {

    @Autowired
    PharmacyInventoryService pharmacyInventoryService;

    @RequestMapping(path = "/inventory")
    public ResponseEntity<?> getAllInventory() {
        return new ResponseEntity<>(pharmacyInventoryService.getAllInventory(), HttpStatus.OK);
    }

}
