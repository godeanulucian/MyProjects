package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.Insurance;
import lucian.ehealth.handlers.RequestHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceDTO implements RequestHandler.ReturnCodeAware {
    private Long insuranceID;
    private String patientFullName;
    private String companyName; // Name of the insurance company
    private LocalDate startDate; // Start date of the insurance coverage
    private LocalDate endDate; // End date of the insurance coverage
    private Double coveragePercent; // Maximum coverage amount provided by the insurance - company pays 60, 70, 80, 90%
    private String contactInformation; // Contact information for the insurance company
    private String returnCode;

    public InsuranceDTO() {}

    public InsuranceDTO(Insurance insurance) {
        insuranceID = insurance.getInsuranceID();
        patientFullName = insurance.getPatientFullName();
        companyName = insurance.getCompanyName();
        startDate = insurance.getStartDate();
        endDate = insurance.getEndDate();
        coveragePercent = insurance.getCoveragePercent();
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
                ", coverageAmount=" + coveragePercent +
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

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
