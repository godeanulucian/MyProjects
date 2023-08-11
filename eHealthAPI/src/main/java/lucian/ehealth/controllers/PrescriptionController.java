package lucian.ehealth.controllers;

import lucian.ehealth.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @RequestMapping(path = "/prescriptions")
    public ResponseEntity<?> getAllPrescriptions() {
        return new ResponseEntity<>(prescriptionService.getAllPrescriptions(), HttpStatus.OK);
    }

}
