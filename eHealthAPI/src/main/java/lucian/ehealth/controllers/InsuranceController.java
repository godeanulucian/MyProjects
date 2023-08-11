package lucian.ehealth.controllers;

import lucian.ehealth.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @RequestMapping(path = "/insurances")
    public ResponseEntity<?> getAllInsurances() {
        return new ResponseEntity<>(insuranceService.getAllInsurances(), HttpStatus.OK);
    }

}
