package lucian.ehealth.controllers;

import lucian.ehealth.dto.PaymentDTO;
import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    PaymentService paymentService;

    // UNAUTHORIZED REQUEST HANDLER
    public ResponseEntity<?> handleUnauthorizedRequest() {
        PaymentDTO response = new PaymentDTO();
        response.setReturnCode("You are not allowed to do this");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllPayments() {
        return handleUnauthorizedRequest();

        // return paymentService.getAllPayments();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> makePayment(PaymentDTO paymentDTO) {
        return paymentService.makePayment(paymentDTO);
    }

    // READ
    @GetMapping(path = "/{paymentID}")
    public ResponseEntity<?> getPayment(@PathVariable Long paymentID) {
        return paymentService.getPayment(paymentID);
    }

    // UPDATE
    @PutMapping(path = "/{paymentID}")
    public ResponseEntity<?> updatePayment(@RequestBody PaymentDTO paymentDTO, @PathVariable Long paymentID) {
        return handleUnauthorizedRequest();

        // return paymentService.updatePayment(paymentDTO, paymentID);
    }

    // DELETE
    @DeleteMapping(path = "/{paymentID}")
    public ResponseEntity<?> deletePayment(@PathVariable Long paymentID) {
        return handleUnauthorizedRequest();

        // return paymentService.deletePayment(paymentID);
    }

}
