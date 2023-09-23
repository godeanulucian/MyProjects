package lucian.ehealth.validators;

import lucian.ehealth.dto.PaymentDTO;
import lucian.ehealth.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {

    @Autowired
    PaymentRepository paymentRepository;

    public boolean matcher(PaymentDTO paymentDTO) {
        return paymentDTO!=null
                && paymentDTO.getProviderFullName().matches("[a-zA-Z .-]{3,128}+")
                && paymentDTO.getProviderCardNumber().matches("[0-9]{16}+")
                && paymentDTO.getPatientFullName().matches("[a-zA-Z .-]{3,128}+")
                && paymentDTO.getPatientCardNumber().matches("[0-9]{16}+")
                // && paymentDTO.getTimestamp()==null // ??
                && paymentDTO.getAmount() >= 0
                // && paymentDTO.getStatus()==null // ??
                && paymentDTO.getDescription().length() <= 128;
    }

    /*public boolean checkAmount(PaymentDTO paymentDTO) {
        return true;
    }*/

    public boolean validatePayment(PaymentDTO paymentDTO) {
        return matcher(paymentDTO) // && checkAmount(paymentDTO)
                && paymentRepository.findByPaymentID(paymentDTO.getPaymentID())==null;
    }

    public boolean validateUpdatePayment(PaymentDTO paymentDTO) {
        return matcher(paymentDTO);
    }

}
