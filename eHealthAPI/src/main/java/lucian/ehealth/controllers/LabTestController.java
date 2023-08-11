package lucian.ehealth.controllers;

import lucian.ehealth.services.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabTestController {

    @Autowired
    LabTestService labTestService;

    @RequestMapping(path = "/lab_tests")
    public ResponseEntity<?> getAllLabTests() {
        return new ResponseEntity<>(labTestService.getAllLabTests(), HttpStatus.OK);
    }

}
