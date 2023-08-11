package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.LabTestDTO;

import java.time.LocalDate;
@Entity
@Table(name = "LAB_TESTS")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labTestID;
    public String testName;
    @OneToOne(mappedBy = "labTest")
    public Patient patient;
    public String patientFullName;
    public String type;
    public LocalDate testDate;
    public String result;
    public String technician;
    public String location;
    public String comments;

    public LabTest() {}

    public LabTest(LabTestDTO labTestDTO) {
        labTestID = labTestDTO.getLabTestID();
        testName = labTestDTO.getTestName();
        patient = labTestDTO.getPatient();
        patientFullName = labTestDTO.getPatientFullName();
        type = labTestDTO.getType();
        testDate = labTestDTO.getTestDate();
        result = labTestDTO.getResult();
        technician = labTestDTO.getTechnician();
        location = labTestDTO.getLocation();
        comments = labTestDTO.getComments();
    }

    @Override
    public String toString() {
        return "LabTest{" +
                "labTestID=" + labTestID +
                ", testName='" + testName + '\'' +
                ", patient=" + patient +
                ", patientFullName='" + patientFullName + '\'' +
                ", type='" + type + '\'' +
                ", testDate=" + testDate +
                ", result='" + result + '\'' +
                ", technician='" + technician + '\'' +
                ", location='" + location + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public Long getLabTestID() {
        return labTestID;
    }

    public void setLabTestID(Long labTestID) {
        this.labTestID = labTestID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
