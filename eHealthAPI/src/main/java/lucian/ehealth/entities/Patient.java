
package lucian.ehealth.entities;
import jakarta.persistence.*;
import lucian.ehealth.dto.PatientDTO;
import java.time.LocalDate;

@Entity
@Table(name = "PATIENTS")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientID;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    @Column(unique=true)
    private String phoneNumber;
    private String socialMedia;
    private String address;
    private boolean hasInsurance;
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
    private boolean hasAppointment;
    private boolean hasPayment;

    @Override
    public String toString() {
        return "Patient{" +
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
                '}';
    }

    public Patient() {}

    public Patient(PatientDTO patientDTO) {
        patientID = patientDTO.getPatientID();
        fullName = patientDTO.getFullName();
        dateOfBirth = patientDTO.getDateOfBirth();
        gender = patientDTO.getGender();
        email = patientDTO.getEmail();
        phoneNumber = patientDTO.getPhoneNumber();
        socialMedia = patientDTO.getSocialMedia();
        address = patientDTO.getAddress();
        hasInsurance = patientDTO.isHasInsurance();
        emergencyContact = patientDTO.getEmergencyContact();
        bloodType = patientDTO.getBloodType();
        height = patientDTO.getHeight();
        weight = patientDTO.getWeight();
        language = patientDTO.getLanguage();
        primaryCarePhysician = patientDTO.getPrimaryCarePhysician();
        allergies = patientDTO.getAllergies();
        medications = patientDTO.getMedications();
        nextOfKinFullName = patientDTO.getNextOfKinFullName();
        testName = patientDTO.getTestName();
        prescriptionName = patientDTO.getPrescriptionName();
        hasAppointment = patientDTO.isHasAppointment();
        hasPayment = patientDTO.isHasPayment();
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


