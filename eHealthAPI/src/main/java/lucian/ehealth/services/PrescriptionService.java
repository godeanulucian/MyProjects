package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.PharmacyInventoryDTO;
import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.entities.Prescription;
import lucian.ehealth.handlers.RequestHandler;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.repositories.PrescriptionRepository;
import lucian.ehealth.validators.PrescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PrescriptionService {

    @Autowired
    PrescriptionDTO prescriptionDTO;
    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    PrescriptionValidator prescriptionValidator;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    public ResponseEntity<?> getAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptionRepository.findAll().forEach(prescriptions::add);
        if (!prescriptions.isEmpty()) {
            return new ResponseEntity<>(prescriptions, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return requestHandler.handleBadRequest("No prescriptions found", new PrescriptionDTO());
        }
    }

    // CREATE
    public ResponseEntity<?> addPrescription(PrescriptionDTO prescriptionDTO) {
        if (prescriptionValidator.validatePrescription(prescriptionDTO)) {
            Prescription prescription = new Prescription(prescriptionDTO);
            PrescriptionDTO response = new PrescriptionDTO(prescriptionRepository.save(prescription));

            Patient patient = patientRepository.findByFullName(prescriptionDTO.getPatientFullName());
            updatePatient(patient, prescriptionDTO.getPrescriptionName());

            System.out.println("\nPrescription with ID: " + response.getPrescriptionID() + " was created");
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return requestHandler.handleBadRequest("Prescription was not created", new PrescriptionDTO());
        }
    }

    // READ
    public ResponseEntity<?> getPrescription(Long prescriptionID) {
        Prescription prescription = prescriptionRepository.findByPrescriptionID(prescriptionID);
        if (prescription != null) {
            PrescriptionDTO response = new PrescriptionDTO(prescription);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } else {
            return requestHandler.handleBadRequest("Prescription not found", new PrescriptionDTO());
        }
    }

    // UPDATE - not allowed
    public ResponseEntity<?> updatePrescription(PrescriptionDTO prescriptionDTO, Long prescriptionID) {
        return requestHandler.handleBadRequest("Prescription not found or update error", new PrescriptionDTO());
    }

    // DELETE - not allowed
    public ResponseEntity<?> deletePrescription(Long prescriptionID) {
        return requestHandler.handleBadRequest("Prescription not found", new PrescriptionDTO());
    }

    public void updatePatient(Patient patient, String prescriptionName) {
        patient.setPrescriptionName(prescriptionName);
    }

}
