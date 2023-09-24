package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.PatientDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.handlers.RequestHandler;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.repositories.UserRepository;
import lucian.ehealth.validators.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    public ResponseEntity<?> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        if(!patients.isEmpty()) {
            return new ResponseEntity<>(patients, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return requestHandler.handleBadRequest("No patients found", new PatientDTO());
        }
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

    // READ
    public ResponseEntity<?> getPatient(String fullName) {
        Patient patient = patientRepository.findByFullName(fullName);
        if(patient!=null) {
            PatientDTO response = new PatientDTO(patient);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return requestHandler.handleBadRequest("Patient not found", new PatientDTO());
        }
    }

    // UPDATE
    public ResponseEntity<?> updatePatient(PatientDTO patientDTO, String fullName) {
        Patient patient = patientRepository.findByFullName(fullName);
        if(patient!=null && patientValidator.validateUpdatePatient(patientDTO)) {
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
            patient.setSocialMedia(patientDTO.getSocialMedia());
            patient.setEmergencyContact(patientDTO.getEmergencyContact());
            patient.setBloodType(patientDTO.getBloodType());
            patient.setHeight(patientDTO.getHeight());
            patient.setWeight(patientDTO.getWeight());
            patient.setLanguage(patientDTO.getLanguage());
            patient.setPrimaryCarePhysician(patientDTO.getPrimaryCarePhysician());
            patient.setAllergies(patientDTO.getAllergies());
            patient.setNextOfKinFullName(patientDTO.getNextOfKinFullName());
            patient.setPrescriptionName(patientDTO.getPrescriptionName());
            patient.setTestName(patientDTO.getTestName());

            PatientDTO response = new PatientDTO(patientRepository.save(patient));
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return requestHandler.handleBadRequest("Patient not found", new PatientDTO());
        }
    }

    // DELETE
    // is not necessary to create a new service because we can use the delete method from user service

}
