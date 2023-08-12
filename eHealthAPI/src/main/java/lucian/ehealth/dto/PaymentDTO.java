package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.entities.Payment;
import lucian.ehealth.entities.Provider;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {
    private Long paymentID;
    private String providerFullName;
    private String patientFullName;
    private Timestamp timestamp;
    private Double amount;
    private String status;
    private String description;
    private String returnCode;

    public PaymentDTO(){}

    public PaymentDTO(Payment payment){
        paymentID = payment.getPaymentID();
        providerFullName = payment.getProviderFullName();
        patientFullName = payment.getPatientFullName();
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
                ", patientFullName='" + patientFullName + '\'' +
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
}
