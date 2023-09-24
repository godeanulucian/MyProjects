package lucian.ehealth.controllers;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.handlers.RequestHandler;
import lucian.ehealth.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/insurances")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllInsurances() {
        return requestHandler.handleUnauthorizedRequest(new InsuranceDTO());

        // return insuranceService.getAllInsurances();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addInsurance(@RequestBody InsuranceDTO insuranceDTO){
        return insuranceService.addInsurance(insuranceDTO);
    }

    // READ
    @GetMapping(path = "/{patientFullName}")
    public ResponseEntity<?> getInsurance(@PathVariable String patientFullName) {
        return insuranceService.getInsurance(patientFullName);
    }

    // UPDATE
    @PutMapping(path = "/{patientFullName}")
    public ResponseEntity<?> updateInsurance(@RequestBody InsuranceDTO insuranceDTO, @PathVariable String patientFullName) {
        return requestHandler.handleUnauthorizedRequest(new InsuranceDTO());

        // return insuranceService.updateInsurance(insuranceDTO, patientFullName);
    }

    // DELETE
    @DeleteMapping(path = "/{patientFullName}")
    public ResponseEntity<?> deleteInsurance(@PathVariable String patientFullName) {
        return insuranceService.deleteInsurance(patientFullName);
    }
}
