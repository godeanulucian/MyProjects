package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.PaymentDTO;

import java.sql.Timestamp;
@Entity
@Table(name = "PAYMENTS")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;
    private String providerFullName;
    private String providerCardNumber;
    private String patientFullName;
    private String patientCardNumber;
    private Timestamp timestamp;
    private Double amount;
    private String status;
    private String description;

    public Payment(){}

    public Payment(PaymentDTO paymentDTO){
        paymentID = paymentDTO.getPaymentID();
        providerFullName = paymentDTO.getProviderFullName();
        providerCardNumber = paymentDTO.getProviderCardNumber();
        patientFullName = paymentDTO.getPatientFullName();
        patientCardNumber = paymentDTO.getPatientCardNumber();
        timestamp = paymentDTO.getTimestamp();
        amount = paymentDTO.getAmount();
        status = paymentDTO.getStatus();
        description = paymentDTO.getDescription();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", providerFullName='" + providerFullName + '\'' +
                ", providerCardNumber='" + providerCardNumber + '\'' +
                ", patientFullName='" + patientFullName + '\'' +
                ", patientCardNumber='" + patientCardNumber + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
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
