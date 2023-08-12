package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.Insurance;
import lucian.ehealth.entities.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceDTO {
    private Long insuranceID;
    private String patientFullName;
    private String companyName; // Name of the insurance company
    private LocalDate startDate; // Start date of the insurance coverage
    private LocalDate endDate; // End date of the insurance coverage
    private double coverageAmount; // Maximum coverage amount provided by the insurance
    private String contactInformation; // Contact information for the insurance company
    private String returnCode;

    public InsuranceDTO() {}

    public InsuranceDTO(Insurance insurance) {
        insuranceID = insurance.getInsuranceID();
        patientFullName = insurance.getPatientFullName();
        companyName = insurance.getCompanyName();
        startDate = insurance.getStartDate();
        endDate = insurance.getEndDate();
        coverageAmount = insurance.getCoverageAmount();
        contactInformation = insurance.getContactInformation();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "InsuranceDTO{" +
                "insuranceID=" + insuranceID +
                ", patientFullName='" + patientFullName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", coverageAmount=" + coverageAmount +
                ", contactInformation='" + contactInformation + '\'' +
                ", returnCode='" + returnCode + '\'' +
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

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
