package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.*;
import lucian.ehealth.handlers.RequestHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO implements RequestHandler.ReturnCodeAware {
    private Long patientID;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String phoneNumber;
    private String socialMedia;
    private String address;
    private Boolean hasInsurance;
    private String emergencyContact;
    private String bloodType;
    private Double height;
    private Double weight;
    private String language;
    private String primaryCarePhysician;
    private String allergies; // List of allergies the patient may have
    private String medications; // List of current medications taken by the patient
    private String nextOfKinFullName;
    private String testName;
    private String prescriptionName;
    private Boolean hasAppointment;
    private Boolean hasPayment;
    private String returnCode;

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patientID=" + patientID +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", socialMedia='" + socialMedia + '\'' +
                ", address='" + address + '\'' +
                ", hasInsurance=" + hasInsurance +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", language='" + language + '\'' +
                ", primaryCarePhysician='" + primaryCarePhysician + '\'' +
                ", allergies='" + allergies + '\'' +
                ", medications='" + medications + '\'' +
                ", nextOfKinFullName='" + nextOfKinFullName + '\'' +
                ", testName='" + testName + '\'' +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", hasAppointment=" + hasAppointment +
                ", hasPayment=" + hasPayment +
                ", returnCode='" + returnCode + '\'' +
                '}';
    }

    public PatientDTO() {}

    public PatientDTO(Patient patient) {
        patientID = patient.getPatientID();
        fullName = patient.getFullName();
        dateOfBirth = patient.getDateOfBirth();
        gender = patient.getGender();
        email = patient.getEmail();
        phoneNumber = patient.getPhoneNumber();
        socialMedia = patient.getSocialMedia();
        address = patient.getAddress();
        hasInsurance = patient.isHasInsurance();
        emergencyContact = patient.getEmergencyContact();
        bloodType = patient.getBloodType();
        height = patient.getHeight();
        weight = patient.getWeight();
        language = patient.getLanguage();
        primaryCarePhysician = patient.getPrimaryCarePhysician();
        allergies = patient.getAllergies();
        medications = patient.getMedications();
        nextOfKinFullName = patient.getNextOfKinFullName();
        testName = patient.getTestName();
        prescriptionName = patient.getPrescriptionName();
        hasAppointment = patient.isHasAppointment();
        hasPayment = patient.isHasPayment();
        this.returnCode = getReturnCode();
    }

    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPrimaryCarePhysician() {
        return primaryCarePhysician;
    }

    public void setPrimaryCarePhysician(String primaryCarePhysician) {
        this.primaryCarePhysician = primaryCarePhysician;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public String getNextOfKinFullName() {
        return nextOfKinFullName;
    }

    public void setNextOfKinFullName(String nextOfKinFullName) {
        this.nextOfKinFullName = nextOfKinFullName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public Boolean isHasAppointment() {
        return hasAppointment;
    }

    public void setHasAppointment(Boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }

    public Boolean isHasPayment() {
        return hasPayment;
    }

    public void setHasPayment(Boolean hasPayment) {
        this.hasPayment = hasPayment;
    }
}
