package lucian.ehealth.controllers;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.services.InsuranceService;
import org.hibernate.annotations.Struct;
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

    public ResponseEntity<?> handleUnauthorizedRequest() {
        InsuranceDTO response = new InsuranceDTO();
        response.setReturnCode("You are not allowed to do this");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllInsurances() {
        InsuranceDTO response = new InsuranceDTO();
        response.setReturnCode("You are not allowed to do this");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);

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
        return handleUnauthorizedRequest();
    }

    // DELETE
    @DeleteMapping(path = "/{patientFullName}")
    public ResponseEntity<?> deleteInsurance(@PathVariable String patientFullName) {
        return insuranceService.deleteInsurance(patientFullName);
    }
}
