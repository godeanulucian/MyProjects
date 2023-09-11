package lucian.ehealth.controllers;

import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // UNAUTHORIZED REQUEST HANDLER
    public ResponseEntity<?> handleUnauthorizedRequest() {
        UserDTO response = new UserDTO();
        response.setReturnCode("You are not allowed to do this");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return handleUnauthorizedRequest();

        // return userService.getAllUsers();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    // READ

}
