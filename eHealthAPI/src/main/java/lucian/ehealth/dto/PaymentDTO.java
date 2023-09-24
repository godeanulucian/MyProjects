package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.Payment;
import lucian.ehealth.handlers.RequestHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO implements RequestHandler.ReturnCodeAware {
    private Long paymentID;
    private String providerFullName;
    private String providerCardNumber;
    private String patientFullName;
    private String patientCardNumber;
    private Timestamp timestamp;
    private Double amount;
    private String status;
    private String description;
    private String returnCode;

    public PaymentDTO(){}

    public PaymentDTO(Payment payment){
        paymentID = payment.getPaymentID();
        providerFullName = payment.getProviderFullName();
        providerCardNumber = payment.getProviderCardNumber();
        patientFullName = payment.getPatientFullName();
        patientCardNumber = payment.getPatientCardNumber();
        timestamp = payment.getTimestamp();
        amount = payment.getAmount();
        status = payment.getStatus();
        description = payment.getDescription();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentID=" + paymentID +
                ", providerFullName='" + providerFullName + '\'' +
                ", providerCardNumber='" + providerCardNumber + '\'' +
                ", patientFullName='" + patientFullName + '\'' +
                ", patientCardNumber='" + patientCardNumber + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", returnCode='" + returnCode + '\'' +
                '}';
    }

    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public String getProviderFullName() {
        return providerFullName;
    }

    public void setProviderFullName(String providerFullName) {
        this.providerFullName = providerFullName;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getProviderCardNumber() {
        return providerCardNumber;
    }

    public void setProviderCardNumber(String providerCardNumber) {
        this.providerCardNumber = providerCardNumber;
    }

    public String getPatientCardNumber() {
        return patientCardNumber;
    }

    public void setPatientCardNumber(String patientCardNumber) {
        this.patientCardNumber = patientCardNumber;
    }
}
