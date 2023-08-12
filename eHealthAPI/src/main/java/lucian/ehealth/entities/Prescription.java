package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.PrescriptionDTO;

import java.time.LocalDate;

// id, name, date, patient name, doctor name, medication, refills, pharmacy, instructions
@Entity
@Table(name = "PRESCRIPTIONS")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionID;
    private String prescriptionName;
    private LocalDate prescriptionDate;
    private String patientFullName;
    private String providerFullName;
    private String medication;
    private Double refills;
    private String pharmacy;
    private String instructions;

    public Prescription() {}

    public Prescription(PrescriptionDTO prescriptionDTO) {
        prescriptionID = prescriptionDTO.getPrescriptionID();
        prescriptionName = prescriptionDTO.getPrescriptionName();
        prescriptionDate = prescriptionDTO.getPrescriptionDate();
        patientFullName = prescriptionDTO.getPatientFullName();
        providerFullName = prescriptionDTO.getProviderFullName();
        medication = prescriptionDTO.getMedication();
        refills = prescriptionDTO.getRefills();
        pharmacy = prescriptionDTO.getPharmacy();
        instructions = prescriptionDTO.getInstructions();
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionID=" + prescriptionID +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                ", patientFullName='" + patientFullName + '\'' +
                ", providerFullName='" + providerFullName + '\'' +
                ", medication='" + medication + '\'' +
                ", refills=" + refills +
                ", pharmacy='" + pharmacy + '\'' +
                ", instructions='" + instructions + '\'' +
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
}
