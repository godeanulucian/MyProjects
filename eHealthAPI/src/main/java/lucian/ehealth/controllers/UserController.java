package lucian.ehealth.controllers;

import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.User;
import lucian.ehealth.handlers.RequestHandler;
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
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return requestHandler.handleUnauthorizedRequest(new UserDTO());

        // return userService.getAllUsers();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    // READ
    @GetMapping(path = "/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    // UPDATE
    @PutMapping(path = "/{username}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable String username) {
        return userService.updateUser(userDTO, username);
    }

    // DELETE
    @DeleteMapping(path = "/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

}
