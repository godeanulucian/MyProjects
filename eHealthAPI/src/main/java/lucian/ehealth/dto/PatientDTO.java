package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {
    private Long patientID;
    public String fullName;
    public LocalDate dateOfBirth;
    public String gender;
    public String email;
    public String phoneNumber;
    public String socialMedia;
    public String address;
    public Insurance insurance; // Insurance Company: XYZ Insurance Company, Policy Number: 123456789, Policy Holder: John Doe (patient's name)
    public boolean hasInsurance;
    public String emergencyContact;
    public String bloodType;
    public Double height;
    public Double weight;
    public String language;
    public String primaryCarePhysician;
    public String allergies; // List of allergies the patient may have
    public String medications; // List of current medications taken by the patient
    public NextOfKin nextOfKin; // name, birth, gender, blood type, relation status, contact info, language
    public String nextOfKinFullName;
    public LabTest labTest; // id, name, patient name, type, date, result, technician, location, comments
    public String testName;
    public Prescription prescription; // id, name, date, patient name, doctor name, medication, refills, pharmacy, instructions
    public String prescriptionName;
    public Appointment appointment; // id, date, time, patient name, doctor name, type, status, reason, lcoation, notes
    public boolean hasAppointment;
    private Payment payment; // id, timestamp, patient name, amount, status, description
    private boolean hasPayment;
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
                ", insurance=" + insurance +
                ", hasInsurance=" + hasInsurance +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", language='" + language + '\'' +
                ", primaryCarePhysician='" + primaryCarePhysician + '\'' +
                ", allergies='" + allergies + '\'' +
                ", medications='" + medications + '\'' +
                ", nextOfKin=" + nextOfKin +
                ", nextOfKinFullName='" + nextOfKinFullName + '\'' +
                ", labTest=" + labTest +
                ", testName='" + testName + '\'' +
                ", prescription=" + prescription +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", appointment=" + appointment +
                ", hasAppointment=" + hasAppointment +
                ", payment=" + payment +
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
        insurance = patient.getInsuranceInformation();
        hasInsurance = patient.isHasInsurance();
        emergencyContact = patient.getEmergencyContact();
        bloodType = patient.getBloodType();
        height = patient.getHeight();
        weight = patient.getWeight();
        language = patient.getLanguage();
        primaryCarePhysician = patient.getPrimaryCarePhysician();
        allergies = patient.getAllergies();
        medications = patient.getMedications();
        nextOfKin = patient.getNextOfKin();
        nextOfKinFullName = patient.getNextOfKinFullName();
        labTest = patient.getLabTest();
        testName = patient.getTestName();
        prescription = patient.getPrescription();
        prescriptionName = patient.getPrescriptionName();
        appointment = patient.getAppointment();
        hasAppointment = patient.isHasAppointment();
        payment = patient.getPayment();
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

    public Insurance getInsuranceInformation() {
        return insurance;
    }

    public void setInsuranceInformation(Insurance insurance) {
        this.insurance = insurance;
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

    public NextOfKin getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(NextOfKin nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public LabTest getLabTest() {
        return labTest;
    }

    public void setLabTest(LabTest labTest) {
        this.labTest = labTest;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
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

    public boolean isHasAppointment() {
        return hasAppointment;
    }

    public void setHasAppointment(boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }

    public boolean isHasPayment() {
        return hasPayment;
    }

    public void setHasPayment(boolean hasPayment) {
        this.hasPayment = hasPayment;
    }
}
