package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.PatientDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.repositories.UserRepository;
import lucian.ehealth.validators.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatientService {
    @Autowired
    PatientDTO patientDTO;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientValidator patientValidator;
    @Autowired
    UserRepository userRepository;

    // READ
    public ResponseEntity<?> getAllPatients() {
        System.out.println("\n--- Patients List ---\n");
        System.out.println(patientRepository.findAll());
        return new ResponseEntity<>(patientRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
    }

    // AUTO CREATE
    public ResponseEntity<?> fetchDataFromUser(UserDTO userDTO) {
        Patient patient = new Patient(patientDTO);
        patient.setFullName(userDTO.getFullName());
        patient.setEmail(userDTO.getEmail());
        patient.setDateOfBirth(userDTO.getDateOfBirth());
        patient.setGender(userDTO.getGender());
        patient.setAddress(userDTO.getAddress());

        PatientDTO response = new PatientDTO(patientRepository.save(patient));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}
