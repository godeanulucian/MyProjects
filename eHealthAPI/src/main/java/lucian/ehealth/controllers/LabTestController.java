package lucian.ehealth.controllers;

import lucian.ehealth.dto.LabTestDTO;
import lucian.ehealth.services.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lab_tests")
public class LabTestController {

    @Autowired
    LabTestService labTestService;

    // UNAUTHORIZED REQUEST HANDLER
    public ResponseEntity<?> handleUnauthorizedRequest() {
        LabTestDTO response = new LabTestDTO();
        response.setReturnCode("You are not allowed to do this");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllLabTests() {
        return handleUnauthorizedRequest();

        // return labTestService.getAllLabTests();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addLabTest(@RequestBody LabTestDTO labTestDTO) {
        return labTestService.addLabTest(labTestDTO);
    }

    // READ
    @GetMapping(path = "/{patientFullName}")
    public ResponseEntity<?> getLabTest(@PathVariable String patientFullName) {
        return labTestService.getLabTest(patientFullName);
    }

    // UPDATE
    @PutMapping(path = "/{patientFullName}")
    public ResponseEntity<?> updateLabTest(@RequestBody LabTestDTO labTestDTO, @PathVariable String patientFullName) {
        return handleUnauthorizedRequest();

        // return labTestService.updateLabTest(labTestDTO, patientFullName);
    }

    // DELETE
    @DeleteMapping(path = "/{patientFullName}")
    public ResponseEntity<?> deleteLabTest(@PathVariable String patientFullName) {
        return handleUnauthorizedRequest();

        // return labTestService.deleteLabTest(patientFullName);
    }

}
