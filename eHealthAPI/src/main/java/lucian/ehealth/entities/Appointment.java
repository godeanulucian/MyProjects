package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.AppointmentDTO;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentID;
    public LocalDate date; // yyyy-mm-dd
    public LocalTime time; // hh:mm:ss
    public String patientName;
    public String providerName;
    public String type;
    public String status;
    public String reason;
    public String location;
    public String notes;


    public Appointment() {}

    public Appointment(AppointmentDTO appointmentDTO) {
        appointmentID = appointmentDTO.getAppointmentID();
        date = appointmentDTO.getDate();
        time = appointmentDTO.getTime();
        patientName = appointmentDTO.getPatientName();
        providerName = appointmentDTO.getProviderName();
        type = appointmentDTO.getType();
        status = appointmentDTO.getStatus();
        reason = appointmentDTO.getReason();
        location = appointmentDTO.getLocation();
        notes = appointmentDTO.getNotes();
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID=" + appointmentID +
                ", date=" + date +
                ", time=" + time +
                ", patientName='" + patientName + '\'' +
                ", providerName='" + providerName + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", location='" + location + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
