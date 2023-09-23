package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.PaymentDTO;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.entities.Payment;
import lucian.ehealth.entities.Provider;
import lucian.ehealth.entities.User;
import lucian.ehealth.repositories.PaymentRepository;
import lucian.ehealth.validators.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PaymentService {

    @Autowired
    PaymentDTO paymentDTO;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentValidator paymentValidator;

    // BAD REQUEST HANDLER
    public ResponseEntity<?> handleBadRequest(String returnCode) {
        PaymentDTO response = new PaymentDTO();
        response.setReturnCode(returnCode);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // READ ALL
    public ResponseEntity<?> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        paymentRepository.findAll().forEach(payments::add);
        if (!payments.isEmpty()) {
            return new ResponseEntity<>(payments, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("No payments found");
        }
    }

    // CREATE
    public ResponseEntity<?> makePayment(PaymentDTO paymentDTO) {
        if (paymentValidator.validatePayment(paymentDTO)) {
            Payment payment = new Payment(paymentDTO);
            PaymentDTO response = new PaymentDTO(paymentRepository.save(payment));

            // some logic

            System.out.println("\nPayment with ID: " + response.getPaymentID() + " was created");
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Payment failed");
        }
    }

    // READ
    public ResponseEntity<?> getPayment(Long paymentID) {
        Payment payment = paymentRepository.findByPaymentID(paymentID);
        if (payment!=null) {
            PaymentDTO response = new PaymentDTO(payment);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Payment not found");
        }
    }

    // UPDATE - not allowed
    public ResponseEntity<?> updatePayment(PaymentDTO paymentDTO, Long paymentID) {
        return handleBadRequest("Payment not found or update error");
    }

    // DELETE
    public ResponseEntity<?> deletePayment(Long paymentID) {
        return handleBadRequest("Payment not found or update error");
    }

    public void updatePaymentStatus(Payment payment, String status) {

    }

    public void updateProviderAndPatient(Provider provider, Patient patient, boolean checker) {

    }

    public void updateUserAmount(User user, Double amount) {

    }

    public void applyInsuranceDiscount(/*???*/) {

    }

}
