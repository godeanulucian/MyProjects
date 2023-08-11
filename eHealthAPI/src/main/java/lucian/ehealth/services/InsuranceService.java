package lucian.ehealth.services;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    @Autowired
    InsuranceDTO insuranceDTO;
    @Autowired
    InsuranceRepository insuranceRepository;

    public ResponseEntity<?> getAllInsurances() {
        System.out.println("\n--- Insurances List ---\n");
        return new ResponseEntity<>(insuranceRepository.findAll(), HttpStatus.OK);
    }

}
