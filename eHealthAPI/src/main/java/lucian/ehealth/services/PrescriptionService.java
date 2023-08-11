package lucian.ehealth.services;

import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionDTO prescriptionDTO;
    @Autowired
    PrescriptionRepository prescriptionRepository;

    public ResponseEntity<?> getAllPrescriptions() {
        System.out.println("\n--- Prescriptions List ---\n");
        return new ResponseEntity<>(prescriptionRepository.findAll(), HttpStatus.OK);
    }

}
