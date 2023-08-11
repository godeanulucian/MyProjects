package lucian.ehealth.services;

import lucian.ehealth.dto.PaymentDTO;
import lucian.ehealth.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentDTO paymentDTO;
    @Autowired
    PaymentRepository paymentRepository;

    public ResponseEntity<?> getAllPayments() {
        System.out.println("\n--- Payments List ---\n");
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }

}
