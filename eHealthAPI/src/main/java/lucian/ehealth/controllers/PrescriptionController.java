package lucian.ehealth.controllers;

import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/prescriptions")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    // UNAUTHORIZED REQUEST HANDLER
    public ResponseEntity<?> handleUnauthorizedRequest() {
        PrescriptionDTO response = new PrescriptionDTO();
        response.setReturnCode("You are not allowed to do this");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllPrescriptions() {
        return handleUnauthorizedRequest();

        // return prescriptionService.getAllPrescriptions();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        return prescriptionService.addPrescription(prescriptionDTO);
    }

    // READ
    @GetMapping(path = "/{prescriptionID}")
    public ResponseEntity<?> getPrescription(@PathVariable Long prescriptionID) {
        return prescriptionService.getPrescription(prescriptionID);
    }

    // UPDATE
    @PutMapping(path = "/{prescriptionID}")
    public ResponseEntity<?> updatePrescription(@RequestBody PrescriptionDTO prescriptionDTO, @PathVariable Long prescriptionID) {
        return handleUnauthorizedRequest();

        // return prescriptionService.updatePrescription(prescriptionDTO, prescriptionID);
    }

    // DELETE
    @DeleteMapping(path = "/{prescriptionID}")
    public ResponseEntity<?> deletePrescription(@PathVariable Long prescriptionID) {
        return handleUnauthorizedRequest();

        // return prescriptionService.deletePrescription(prescriptionID);
    }

}