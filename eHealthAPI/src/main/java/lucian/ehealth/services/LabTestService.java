package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.LabTestDTO;
import lucian.ehealth.entities.LabTest;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.repositories.LabTestRepository;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.validators.LabTestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabTestService {

    @Autowired
    LabTestDTO labTestDTO;
    @Autowired
    LabTestRepository labTestRepository;
    @Autowired
    LabTestValidator labTestValidator;
    @Autowired
    PatientRepository patientRepository;

    // BAD REQUEST HANDLER
    public ResponseEntity<?> handleBadRequest(String returnCode) {
        LabTestDTO response = new LabTestDTO();
        response.setReturnCode(returnCode);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // READ ALL
    public ResponseEntity<?> getAllLabTests() {
        List<LabTest> labTests = new ArrayList<>();
        labTestRepository.findAll().forEach(labTests::add);
        if (!labTests.isEmpty()) {
            return new ResponseEntity<>(labTests, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("No laboratory tests found");
        }
    }

    // CREATE
    public ResponseEntity<?> addLabTest(LabTestDTO labTestDTO) {
        if (labTestValidator.validateLabTest(labTestDTO)) {
            LabTest labTest = new LabTest(labTestDTO);
            LabTestDTO response = new LabTestDTO(labTestRepository.save(labTest));

            Patient patient = patientRepository.findByFullName(labTestDTO.getPatientFullName());
            updatePatient(patient, labTestDTO.getTestName());

            System.out.println("\nLaboratory test with ID: " + response.getLabTestID() + " was created");
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Laboratory test was not created");
        }
    }

    // READ
    public ResponseEntity<?> getLabTest(String patientFullName) {
        LabTest labTest = labTestRepository.findByPatientFullName(patientFullName);
        if (labTest != null) {
            LabTestDTO response = new LabTestDTO(labTest);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Laboratory test not found");
        }
    }

    // UPDATE
    public ResponseEntity<?> updateLabTest(LabTestDTO labTestDTO, String patientFullName) {
        LabTest labTest = labTestRepository.findByPatientFullName(patientFullName);
        if (labTest != null && labTestValidator.validateUpdateLabTest(labTestDTO)) {
            labTest.setTestName(labTestDTO.getTestName());
            labTest.setPatientFullName(labTestDTO.getPatientFullName());
            labTest.setType(labTestDTO.getType());
            labTest.setTestDate(labTestDTO.getTestDate());
            labTest.setResult(labTestDTO.getResult());
            labTest.setTechnician(labTestDTO.getTechnician());
            labTest.setLocation(labTest.getLocation());
            labTest.setComments(labTest.getComments());

            Patient patient = patientRepository.findByFullName(labTestDTO.getPatientFullName());
            updatePatient(patient, labTestDTO.getTestName());

            LabTestDTO response = new LabTestDTO(labTestRepository.save(labTest));
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Laboratory test not found or update error");
        }
    }

    // DELETE
    public ResponseEntity<?> deleteLabTest(String patientFullName) {
        LabTest labTest = labTestRepository.findByPatientFullName(patientFullName);
        if (labTest != null) {
            labTestRepository.delete(labTest);
            LabTestDTO response = new LabTestDTO();
            response.setReturnCode("Laboratory test deleted");

            Patient patient = patientRepository.findByFullName(patientFullName);
            updatePatient(patient, "None");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

        }
        else {
            return handleBadRequest("Laboratory test not found");
        }
    }

    public void updatePatient(Patient patient, String testName) {
        patient.setTestName(testName);
    }

}
