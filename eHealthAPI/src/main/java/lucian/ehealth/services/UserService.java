package lucian.ehealth.services;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.User;
import lucian.ehealth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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
        User user = new User(userDTO);
        if(!userDTO.isDoctor())
            patientService.fetchDataFromUser(userDTO);
        else
            providerService.fetchDataFromUser(userDTO);
        // UserDTO response = new UserDTO(userRepository.save(new User(userDTO)));
        userRepository.save(user);

        userDTO.setReturnCode("\nuser created successfully\n");
        System.out.println(userDTO.getReturnCode());
        UserDTO response = new UserDTO(user);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

        // rework method
    }

}
