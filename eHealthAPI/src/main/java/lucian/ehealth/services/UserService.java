package lucian.ehealth.services;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.User;
import lucian.ehealth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    // READ
    public ResponseEntity<?> getAllUsers() {
        System.out.println("\n--- Users List ---\n");
        System.out.println(userRepository.findAll());
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    // CREATE
    // auto create patient
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
    }

}
