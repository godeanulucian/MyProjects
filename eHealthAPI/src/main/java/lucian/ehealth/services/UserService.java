package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.entities.Provider;
import lucian.ehealth.entities.User;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.repositories.ProviderRepository;
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
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ProviderRepository providerRepository;

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
                System.out.println("\nPatient type user with ID: " + response.getUserID() + " was created");
            }
            else {
                providerService.fetchDataFromUser(userDTO);
                System.out.println("\nProvider type user with ID: " + response.getUserID() + " was created");
            }

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("User was not created");
        }

    }

    // READ
    public ResponseEntity<?> getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            UserDTO response = new UserDTO(user);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("User not found");
        }
    }

    // UPDATE
    public ResponseEntity<?> updateUser(UserDTO userDTO, String username) {
        User user = userRepository.findByUsername(username);
        if (user!=null && userValidator.validateUpdateUser(userDTO)) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setContactInformation(userDTO.getContactInformation());
            /*user.setDateOfBirth(userDTO.getDateOfBirth()); user/pat/prov can't update dob, gender or isDoc
            user.setGender(userDTO.getGender());
            user.setDoctor(userDTO.isDoctor());*/
            user.setAddress(userDTO.getAddress());
            user.setCardNumber(userDTO.getCardNumber());
            user.setAmount(userDTO.getAmount());
            userRepository.save(user);

            // update patient/doctor too
            if (!userDTO.isDoctor()) {
                Patient patient = patientRepository.findByFullName(user.getFullName());
                updatePatient(patient, userDTO);
            }
            else {
                Provider provider = providerRepository.findByFullName(user.getFullName());
                updateProvider(provider, userDTO);
            }
            user.setFullName(userDTO.getFullName()); // update user full name after updating pat/prov

            UserDTO response = new UserDTO(user);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("User not found or update error");
        }
    }

    // DELETE
    public ResponseEntity<?> deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user!=null) {
            userRepository.delete(user);
            UserDTO response = new UserDTO();
            response.setReturnCode("User deleted");

            // delete patient/doctor too
            if (!user.isDoctor()) {
                Patient patient = patientRepository.findByFullName(user.getFullName());
                patientRepository.delete(patient);
            }
            else {
                Provider provider = providerRepository.findByFullName(user.getFullName());
                providerRepository.delete(provider);
            }

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("User not found");
        }
    }

    private void updatePatient(Patient patient, UserDTO userDTO) {
        patient.setFullName(userDTO.getFullName());
        patient.setEmail(userDTO.getEmail());
        patient.setDateOfBirth(userDTO.getDateOfBirth());
        patient.setGender(userDTO.getGender());
        patient.setAddress(userDTO.getAddress());
    }

    private void updateProvider(Provider provider, UserDTO userDTO) {
        provider.setFullName(userDTO.getFullName());
        provider.setContactInformation(userDTO.getContactInformation());
        provider.setAddress(userDTO.getAddress());
    }

}
