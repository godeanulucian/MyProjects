package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.Prescription;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrescriptionDTO {
    private Long prescriptionID;
    private String prescriptionName;
    private LocalDate prescriptionDate;
    private String patientFullName;
    private String providerFullName;
    private String medication;
    private Double refills;
    private String pharmacy;
    private String instructions;
    private String returnCode;

    public PrescriptionDTO(){}

    public PrescriptionDTO(Prescription prescription){
        prescriptionID = prescription.getPrescriptionID();
        prescriptionName = prescription.getPrescriptionName();
        prescriptionDate = prescription.getPrescriptionDate();
        patientFullName = prescription.getPatientFullName();
        providerFullName = prescription.getProviderFullName();
        medication = prescription.getMedication();
        refills = prescription.getRefills();
        pharmacy = prescription.getPharmacy();
        instructions = prescription.getInstructions();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "PrescriptionDTO{" +
                "prescriptionID=" + prescriptionID +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                ", patientFullName='" + patientFullName + '\'' +
                ", providerFullName='" + providerFullName + '\'' +
                ", medication='" + medication + '\'' +
                ", refills=" + refills +
                ", pharmacy='" + pharmacy + '\'' +
                ", instructions='" + instructions + '\'' +
                ", returnCode='" + returnCode + '\'' +
                '}';
    }

    public Long getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(Long prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getProviderFullName() {
        return providerFullName;
    }

    public void setProviderFullName(String providerFullName) {
        this.providerFullName = providerFullName;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public Double getRefills() {
        return refills;
    }

    public void setRefills(Double refills) {
        this.refills = refills;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
