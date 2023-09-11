package lucian.ehealth.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping
    public ResponseEntity<?> welcomeMessage() {
        String response = "Welcome to the main page of the eHealth REST API!";
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}
