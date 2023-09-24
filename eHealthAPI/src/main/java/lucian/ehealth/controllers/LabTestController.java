package lucian.ehealth.controllers;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.dto.LabTestDTO;
import lucian.ehealth.handlers.RequestHandler;
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
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllLabTests() {
        return requestHandler.handleUnauthorizedRequest(new LabTestDTO());

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
        return requestHandler.handleUnauthorizedRequest(new LabTestDTO());

        // return labTestService.updateLabTest(labTestDTO, patientFullName);
    }

    // DELETE
    @DeleteMapping(path = "/{patientFullName}")
    public ResponseEntity<?> deleteLabTest(@PathVariable String patientFullName) {
        return requestHandler.handleUnauthorizedRequest(new LabTestDTO());

        // return labTestService.deleteLabTest(patientFullName);
    }

}
