package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.entities.Insurance;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.handlers.RequestHandler;
import lucian.ehealth.repositories.InsuranceRepository;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.validators.InsuranceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    public ResponseEntity<?> getAllInsurances() {
        List<Insurance> insurances = new ArrayList<>();
        insuranceRepository.findAll().forEach(insurances::add);
        if (!insurances.isEmpty())
            return new ResponseEntity<>(insurances, new HttpHeaders(), HttpStatus.OK);
        else
            return requestHandler.handleBadRequest("No insurances found", new InsuranceDTO());
    }

    // CREATE
    public ResponseEntity<?> addInsurance(InsuranceDTO insuranceDTO){
        if (insuranceValidator.validateInsurance(insuranceDTO)){
            Insurance insurance = new Insurance(insuranceDTO);
            InsuranceDTO response = new InsuranceDTO(insuranceRepository.save(insurance));

            Patient patient = patientRepository.findByFullName(insuranceDTO.getPatientFullName());
            updatePatient(patient, true);

            System.out.println("\nInsurance with ID: " + response.getInsuranceID() + " was created");
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return requestHandler.handleBadRequest("Insurance was not created", new InsuranceDTO());
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
            return requestHandler.handleBadRequest("Insurance not found", new InsuranceDTO());
        }
    }

    // UPDATE
    public ResponseEntity<?> updateInsurance(InsuranceDTO insuranceDTO, String patientFullName) {
        Insurance insurance = insuranceRepository.findByPatientFullName(patientFullName);
        if (insurance!=null && insuranceValidator.validateUpdateInsurance(insuranceDTO)) {
            insurance.setPatientFullName(insuranceDTO.getPatientFullName());
            insurance.setCompanyName(insuranceDTO.getCompanyName());
            insurance.setStartDate(insuranceDTO.getStartDate());
            insurance.setEndDate(insuranceDTO.getEndDate());
            insurance.setCoveragePercent(insuranceDTO.getCoveragePercent());
            insurance.setContactInformation(insuranceDTO.getContactInformation());

            InsuranceDTO response = new InsuranceDTO(insuranceRepository.save(insurance));
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return requestHandler.handleBadRequest("Insurance not found or update error", new InsuranceDTO());
        }
    }

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
            return requestHandler.handleBadRequest("Insurance not found", new InsuranceDTO());
        }
    }

    public void updatePatient(Patient patient, boolean checker) {
        patient.setHasInsurance(checker);
    }

}
