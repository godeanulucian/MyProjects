package lucian.ehealth.controllers;

import lucian.ehealth.dto.PaymentDTO;
import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.handlers.RequestHandler;
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
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllPayments() {
        return requestHandler.handleUnauthorizedRequest(new PaymentDTO());

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
        return requestHandler.handleUnauthorizedRequest(new PaymentDTO());

        // return paymentService.updatePayment(paymentDTO, paymentID);
    }

    // DELETE
    @DeleteMapping(path = "/{paymentID}")
    public ResponseEntity<?> deletePayment(@PathVariable Long paymentID) {
        return requestHandler.handleUnauthorizedRequest(new PaymentDTO());

        // return paymentService.deletePayment(paymentID);
    }

}
