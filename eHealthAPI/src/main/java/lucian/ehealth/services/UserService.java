package lucian.ehealth.services;

import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDTO userDTO;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> getAllUsers() {
        System.out.println("\n--- Users List ---\n");
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

}
