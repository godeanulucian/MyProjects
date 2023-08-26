package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.entities.Insurance;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.repositories.InsuranceRepository;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.validators.InsuranceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class InsuranceService {

    @Autowired
    InsuranceDTO insuranceDTO;
    @Autowired
    InsuranceRepository insuranceRepository;
    @Autowired
    InsuranceValidator insuranceValidator;
    @Autowired
    PatientRepository patientRepository;

    // BAD REQUEST HANDLER
    public ResponseEntity<?> handleBadRequest(String returnCode) {
        InsuranceDTO response = new InsuranceDTO();
        response.setReturnCode(returnCode);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // READ ALL
    public ResponseEntity<?> getAllInsurances() {
        if (insuranceRepository.findAll()!=null)
            return new ResponseEntity<>(insuranceRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
        else
            return handleBadRequest("No insurances found");
    }

    // CREATE
    public ResponseEntity<?> addInsurance(InsuranceDTO insuranceDTO){
        if (insuranceValidator.validateInsurance(insuranceDTO)){
            Insurance insurance = new Insurance(insuranceDTO);
            InsuranceDTO response = new InsuranceDTO(insuranceRepository.save(insurance));
            Patient patient = patientRepository.findByFullName(insuranceDTO.getPatientFullName());
            updatePatient(patient, true);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Insurance was not created");
        }
    }

    // READ
    public ResponseEntity<?> getInsurance(String patientFullName) {
        Insurance insurance = insuranceRepository.findByPatientFullName(patientFullName);
        if (insurance != null) {
            InsuranceDTO response = new InsuranceDTO(insurance);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Insurance not found");
        }
    }

    // UPDATE

    // DELETE
    public ResponseEntity<?> deleteInsurance(String patientFullName) {
        Insurance insurance = insuranceRepository.findByPatientFullName(patientFullName);
        if (insurance != null) {
            insuranceRepository.delete(insurance);
            InsuranceDTO response = new InsuranceDTO();
            response.setReturnCode("Insurance deleted");

            Patient patient = patientRepository.findByFullName(patientFullName);
            updatePatient(patient, false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Insurance not found");
        }
    }

    private void updatePatient(Patient patient, boolean checker) {
        patient.setHasInsurance(checker);
    }

}
