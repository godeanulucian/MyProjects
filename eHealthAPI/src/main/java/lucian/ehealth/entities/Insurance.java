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
    private String patientFullName;
    private String companyName; // Name of the insurance company
    private LocalDate startDate; // Start date of the insurance coverage
    private LocalDate endDate; // End date of the insurance coverage
    private Double coveragePercent; // Maximum coverage amount provided by the insurance
    private String contactInformation; // Contact information for the insurance company

    public Insurance() {}
    public Insurance(InsuranceDTO insuranceDTO) {
        insuranceID = insuranceDTO.getInsuranceID();
        patientFullName = insuranceDTO.getPatientFullName();
        companyName = insuranceDTO.getCompanyName();
        startDate = insuranceDTO.getStartDate();
        endDate = insuranceDTO.getEndDate();
        coveragePercent = insuranceDTO.getCoveragePercent();
        contactInformation = insuranceDTO.getContactInformation();
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "insuranceID=" + insuranceID +
                ", patientFullName='" + patientFullName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", coverageAmount=" + coveragePercent +
                ", contactInformation='" + contactInformation + '\'' +
                '}';
    }

    public Long getInsuranceID() {
        return insuranceID;
    }
    public void setInsuranceID(Long insuranceID) {
        this.insuranceID = insuranceID;
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

    public Double getCoveragePercent() {
        return coveragePercent;
    }

    public void setCoveragePercent(Double coveragePercent) {
        this.coveragePercent = coveragePercent;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
}
