package lucian.ehealth.controllers;

import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/")
    public ResponseEntity<?> welcomeMessage() {
        String response = "Welcome to the main page of the REST API!";
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(path = "/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.addUser(userDTO), new HttpHeaders(), HttpStatus.OK);
    }

}
