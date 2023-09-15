package lucian.ehealth.controllers;

import lucian.ehealth.dto.PatientDTO;
import lucian.ehealth.dto.UserDTO;
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

    // UNAUTHORIZED REQUEST HANDLER
    public ResponseEntity<?> handleUnauthorizedRequest() {
        PatientDTO response = new PatientDTO();
        response.setReturnCode("You are not allowed to do this");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllPatients() {
        // return handleUnauthorizedRequest();

        return patientService.getAllPatients();
    }

    // AUTO CREATE
    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody UserDTO userDTO) {
        return patientService.fetchDataFromUser(userDTO);
    }



}