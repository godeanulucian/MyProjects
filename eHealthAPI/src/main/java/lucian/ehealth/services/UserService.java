package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.User;
import lucian.ehealth.repositories.UserRepository;
import lucian.ehealth.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class UserService {

    @Autowired
    UserDTO userDTO;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    ProviderService providerService;
    @Autowired
    UserValidator userValidator;

    // BAD REQUEST HANDLER
    public ResponseEntity<?> handleBadRequest(String returnCode) {
        UserDTO response = new UserDTO();
        response.setReturnCode(returnCode);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // READ ALL
    public ResponseEntity<?> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("No users found");
        }
    }

    // CREATE USER & auto create patient/provider
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {

        if (userValidator.validateUser(userDTO)) {
            User user = new User(userDTO);
            UserDTO response = new UserDTO(userRepository.save(user));

            if(!userDTO.isDoctor()) {
                patientService.fetchDataFromUser(userDTO);
            }
            else {
                providerService.fetchDataFromUser(userDTO);
            }

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("User was not created");
        }

    }

    // READ

}
