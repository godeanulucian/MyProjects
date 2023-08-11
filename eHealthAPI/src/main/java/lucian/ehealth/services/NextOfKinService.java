package lucian.ehealth.services;

import lucian.ehealth.dto.NextOfKinDTO;
import lucian.ehealth.repositories.NextOfKinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NextOfKinService {

    @Autowired
    NextOfKinDTO nextOfKinDTO;
    @Autowired
    NextOfKinRepository nextOfKinRepository;

    public ResponseEntity<?> getAllKins() {
        System.out.println("\n--- Kins List ---\n");
        return new ResponseEntity<>(nextOfKinRepository.findAll(), HttpStatus.OK);
    }

}
