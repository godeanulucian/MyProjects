package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.NextOfKinDTO;

import java.time.LocalDate;

@Entity
@Table(name = "KINS")
public class NextOfKin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kinID;
    @OneToOne(mappedBy = "nextOfKin")
    public Patient patient;
    public String patientFullName;

    public String fullName;
    public LocalDate dateOfBirth;
    public String gender;
    public String bloodType;
    public String relationStatus;
    public String contactInfo;
    public String language;

    public NextOfKin() {}
    public NextOfKin(NextOfKinDTO nextOfKinDTO) {
        kinID = nextOfKinDTO.getKinID();
        patient = nextOfKinDTO.getPatient();
        patientFullName = nextOfKinDTO.getPatientFullName();
        fullName = nextOfKinDTO.getFullName();
        dateOfBirth = nextOfKinDTO.getDateOfBirth();
        gender = nextOfKinDTO.getGender();
        bloodType = nextOfKinDTO.getBloodType();
        relationStatus = nextOfKinDTO.getRelationStatus();
        contactInfo = nextOfKinDTO.getContactInfo();
        language = nextOfKinDTO.getLanguage();
    }

    @Override
    public String toString() {
        return "NextOfKin{" +
                "kinID=" + kinID +
                ", patient=" + patient +
                ", patientFullName='" + patientFullName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", relationStatus='" + relationStatus + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public Long getKinID() {
        return kinID;
    }

    public void setKinID(Long kinID) {
        this.kinID = kinID;
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
}
