package lucian.ehealth.controllers;

import lucian.ehealth.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping(path = "/patients")
    public ResponseEntity<?> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), new HttpHeaders(), HttpStatus.OK);
    }



}