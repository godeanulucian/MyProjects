package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL) // outputs only non-null fields in json response
public class AppointmentDTO {
    private Long appointmentID;
    private LocalDate date;
    private LocalTime time;
    private String patientName;
    private String providerName;
    private String type;
    private String status;
    private String reason;
    private String location;
    private String notes;
    private String returnCode;

    public AppointmentDTO() {}

    public AppointmentDTO(Appointment appointment) {
        appointmentID = appointment.getAppointmentID();
        date = appointment.getDate();
        time = appointment.getTime();
        patientName = appointment.getPatientName();
        providerName = appointment.getProviderName();
        type = appointment.getType();
        status = appointment.getStatus();
        reason = appointment.getReason();
        location = appointment.getLocation();
        notes = appointment.getNotes();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
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
                ", returnCode='" + returnCode + '\'' +
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

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
