package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.InsuranceDTO;

import java.time.LocalDate;
@Entity
@Table(name = "INSURANCES")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceID;
    @OneToOne(mappedBy = "insurance")
    public Patient patient;
    public String patientFullName;
    public String companyName; // Name of the insurance company
    public LocalDate startDate; // Start date of the insurance coverage
    public LocalDate endDate; // End date of the insurance coverage
    public double coverageAmount; // Maximum coverage amount provided by the insurance
    public String contactInformation; // Contact information for the insurance company

    public Insurance() {}
    public Insurance(InsuranceDTO insuranceDTO) {
        insuranceID = insuranceDTO.getInsuranceID();
        patient = insuranceDTO.getPatient();
        patientFullName = insuranceDTO.getPatientFullName();
        companyName = insuranceDTO.getCompanyName();
        startDate = insuranceDTO.getStartDate();
        endDate = insuranceDTO.getEndDate();
        coverageAmount = insuranceDTO.getCoverageAmount();
        contactInformation = insuranceDTO.getContactInformation();
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "insuranceID=" + insuranceID +
                ", patient=" + patient +
                ", patientFullName='" + patientFullName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", coverageAmount=" + coverageAmount +
                ", contactInformation='" + contactInformation + '\'' +
                '}';
    }

    public Long getInsuranceID() {
        return insuranceID;
    }
    public void setInsuranceID(Long insuranceID) {
        this.insuranceID = insuranceID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
}
