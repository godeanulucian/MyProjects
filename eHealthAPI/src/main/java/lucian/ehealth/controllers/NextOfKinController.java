package lucian.ehealth.controllers;

import lucian.ehealth.services.NextOfKinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NextOfKinController {

    @Autowired
    NextOfKinService nextOfKinService;

    @RequestMapping(path = "/kins")
    public ResponseEntity<?> getAllKins() {
        return new ResponseEntity<>(nextOfKinService.getAllKins(), HttpStatus.OK);
    }

}
