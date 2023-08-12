package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.LabTest;
import lucian.ehealth.entities.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabTestDTO {
    private Long labTestID;
    private String testName;
    private String patientFullName;
    private String type;
    private LocalDate testDate;
    private String result;
    private String technician;
    private String location;
    private String comments;
    private String returnCode;

    public LabTestDTO() {}

    public LabTestDTO(LabTest labTest) {
        labTestID = labTest.getLabTestID();
        testName = labTest.getTestName();
        patientFullName = labTest.getPatientFullName();
        type = labTest.getType();
        testDate = labTest.getTestDate();
        result = labTest.getResult();
        technician = labTest.getTechnician();
        location = labTest.getLocation();
        comments = labTest.getComments();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "LabTestDTO{" +
                "labTestID=" + labTestID +
                ", testName='" + testName + '\'' +
                ", patientFullName='" + patientFullName + '\'' +
                ", type='" + type + '\'' +
                ", testDate=" + testDate +
                ", result='" + result + '\'' +
                ", technician='" + technician + '\'' +
                ", location='" + location + '\'' +
                ", comments='" + comments + '\'' +
                ", returnCode='" + returnCode + '\'' +
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

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
