package lucian.ehealth.controllers;

import lucian.ehealth.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping(path = "/payments")
    public ResponseEntity<?> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

}
