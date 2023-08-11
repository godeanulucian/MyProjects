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
    @OneToOne(mappedBy = "payment")
    private Provider provider;
    @OneToOne(mappedBy = "payment")
    private Patient patient;
    private String providerFullName;
    private String patientFullName;
    private Timestamp timestamp;
    private Double amount;
    public String status;
    private String description;

    public Payment(){}

    public Payment(PaymentDTO paymentDTO){
        paymentID = paymentDTO.getPaymentID();
        provider = paymentDTO.getProvider();
        patient = paymentDTO.getPatient();
        providerFullName = paymentDTO.getProviderFullName();
        patientFullName = paymentDTO.getPatientFullName();
        timestamp = paymentDTO.getTimestamp();
        amount = paymentDTO.getAmount();
        status = paymentDTO.getStatus();
        description = paymentDTO.getDescription();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", provider=" + provider +
                ", patient=" + patient +
                ", providerFullName='" + providerFullName + '\'' +
                ", patientFullName='" + patientFullName + '\'' +
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
}
