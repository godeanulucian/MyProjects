package lucian.ehealth.validators;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentValidator {

    @Autowired
    AppointmentRepository appointmentRepository;

    public boolean matcher(AppointmentDTO appointmentDTO) {
        return appointmentDTO!=null
                && appointmentDTO.getDate().isAfter(java.time.LocalDate.now())
                // && appointmentDTO.getTime().isAfter(java.time.LocalTime.now())
                // && appointmentDTO.getStatus()==null // ??
                && appointmentDTO.getPatientName().matches("[a-zA-Z .-]{3,128}+")
                && appointmentDTO.getProviderName().matches("[a-zA-Z .-]{3,128}+");
    }
    public boolean validateAppointment(AppointmentDTO appointmentDTO) {
        return matcher(appointmentDTO)
                // appointment will only be valid if it's date, time and provider does not interfere with another appointment
                && appointmentRepository
                .findByDateAndTimeAndPatientNameAndProviderName(appointmentDTO.getDate(), appointmentDTO.getTime(), appointmentDTO.getPatientName(), appointmentDTO.getProviderName())==null;
    }
    public boolean validateUpdateAppointment(AppointmentDTO appointmentDTO) {
        return matcher(appointmentDTO);
    }

}
