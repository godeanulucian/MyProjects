
package lucian.ehealth.entities;
import jakarta.persistence.*;
import lucian.ehealth.dto.PatientDTO;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PATIENTS")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientID;
    @Column(unique=true)
    public String fullName;
    public LocalDate dateOfBirth;
    public String gender;
    public String email;
    public String phoneNumber;
    public String socialMedia;
    public String address;
    @OneToOne // relationship type between classes, useful for another table to be created
    // @Embedded // indicate that your custom class should be embedded in the entity and stored as part of its table.
    public InsuranceInformation insuranceInformation; // Insurance Company: XYZ Insurance Company, Policy Number: 123456789, Policy Holder: John Doe (patient's name)
    public String emergencyContact;
    public String bloodType;
    public Double height;
    public Double weight;
    public String language;
    public String primaryCarePhysician;
    @ElementCollection
    public List<String> allergies; // List of allergies the patient may have
    @ElementCollection
    public List<String> medications; // List of current medications taken by the patient
    /*public NextOfKin nextOfKin; // name, birth, gender, blood type, relation status, contact info, language
    public LabTest labTest; // id, name, patient name, type, date, result, technician, location, comments
    public Prescription prescription; // id, name, date, patient name, doctor name, medication, refills, pharmacy, instructions
    public Appointment appointment; // id, date, time, patient name, doctor name, type, status, reason, lcoation, notes
    private Payment payment;*/ // (transactions) id, timestamp, patient name, amount, status, description

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
                ", insuranceInformation=" + insuranceInformation +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", language='" + language + '\'' +
                ", primaryCarePhysician='" + primaryCarePhysician + '\'' +
                ", allergies=" + allergies +
                ", medications=" + medications +
                /*", nextOfKin=" + nextOfKin +
                ", labTest=" + labTest +
                ", prescription=" + prescription +
                ", appointment=" + appointment +
                ", payment=" + payment*/ +
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
        insuranceInformation = patientDTO.getInsuranceInformation();
        emergencyContact = patientDTO.getEmergencyContact();
        bloodType = patientDTO.getBloodType();
        height = patientDTO.getHeight();
        weight = patientDTO.getWeight();
        language = patientDTO.getLanguage();
        primaryCarePhysician = patientDTO.getPrimaryCarePhysician();
        allergies = patientDTO.getAllergies();
        medications = patientDTO.getMedications();
        /*nextOfKin = patientDTO.getNextOfKin();
        labTest = patientDTO.getLabTest();
        prescription = patientDTO.getPrescription();
        appointment = patientDTO.getAppointment();
        payment = patientDTO.getPayment();*/
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

    public InsuranceInformation getInsuranceInformation() {
        return insuranceInformation;
    }

    public void setInsuranceInformation(InsuranceInformation insuranceInformation) {
        this.insuranceInformation = insuranceInformation;
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

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

   /* public NextOfKin getNextOfKin() {
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
    }*/
}


