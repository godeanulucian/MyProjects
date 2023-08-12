package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.NextOfKin;
import lucian.ehealth.entities.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NextOfKinDTO {
    private Long kinID;
    private String patientFullName;

    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodType;
    private String relationStatus;
    private String contactInfo;
    private String language;
    private String returnCode;

    public NextOfKinDTO(){}

    public NextOfKinDTO(NextOfKin nextOfKin){
        kinID = nextOfKin.getKinID();
        patientFullName = nextOfKin.getPatientFullName();
        fullName = nextOfKin.getFullName();
        dateOfBirth = nextOfKin.getDateOfBirth();
        gender = nextOfKin.getGender();
        bloodType = nextOfKin.getBloodType();
        relationStatus = nextOfKin.getRelationStatus();
        contactInfo = nextOfKin.getContactInfo();
        language = nextOfKin.getLanguage();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "NextOfKinDTO{" +
                "kinID=" + kinID +
                ", patientFullName='" + patientFullName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", relationStatus='" + relationStatus + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", language='" + language + '\'' +
                ", returnCode='" + returnCode + '\'' +
                '}';
    }

    public Long getKinID() {
        return kinID;
    }

    public void setKinID(Long kinID) {
        this.kinID = kinID;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(String relationStatus) {
        this.relationStatus = relationStatus;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
