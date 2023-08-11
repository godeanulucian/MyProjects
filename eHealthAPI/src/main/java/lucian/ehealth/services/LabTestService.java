package lucian.ehealth.services;

import lucian.ehealth.dto.LabTestDTO;
import lucian.ehealth.repositories.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LabTestService {

    @Autowired
    LabTestDTO labTestDTO;
    @Autowired
    LabTestRepository labTestRepository;

    public ResponseEntity<?> getAllLabTests() {
        System.out.println("\n--- Lab Tests List ---\n");
        return new ResponseEntity<>(labTestRepository.findAll(), HttpStatus.OK);
    }

}
