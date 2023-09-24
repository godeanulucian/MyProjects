package lucian.ehealth.controllers;

import lucian.ehealth.dto.LabTestDTO;
import lucian.ehealth.dto.PatientDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.handlers.RequestHandler;
import lucian.ehealth.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/patients")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllPatients() {
        return requestHandler.handleUnauthorizedRequest(new PatientDTO());

        // return patientService.getAllPatients();
    }

    // AUTO CREATE
    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody UserDTO userDTO) {
        return patientService.fetchDataFromUser(userDTO);
    }

    // READ
    @GetMapping(path = "/{fullName}")
    public ResponseEntity<?> getPatient(@PathVariable String fullName) {
        return patientService.getPatient(fullName);
    }

    // UPDATE
    @PutMapping(path = "/{fullName}")
    public ResponseEntity<?> updatePatient(@RequestBody PatientDTO patientDTO, @PathVariable String fullName) {
        return patientService.updatePatient(patientDTO, fullName);
    }

}