package lucian.ehealth.validators;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentValidator {
    @Autowired
    AppointmentRepository appointmentRepository;
    public boolean validateAppointment(AppointmentDTO appointmentDTO) {
        // check the DTO and it's fields to be valid and not null
        return appointmentDTO!=null
                && appointmentDTO.getDate()!=null && appointmentDTO.getDate().isAfter(java.time.LocalDate.now())
                && appointmentDTO.getTime()!=null // && appointmentDTO.getTime().isAfter(java.time.LocalTime.now())
                && appointmentDTO.getPatientName()!=null && appointmentDTO.getPatientName().matches("[a-zA-Z .-]{3,128}+")
                && appointmentDTO.getProviderName()!=null && appointmentDTO.getProviderName().matches("[a-zA-Z .-]{3,128}+")
                && appointmentRepository.findByAppointmentID(appointmentDTO.getAppointmentID())==null;
    }

    public boolean validateBookAppointment(AppointmentDTO appointmentDTO) {
        return validateAppointment(appointmentDTO)
                // appointment will only be valid if it's date, time and provider does not interfere with another appointment
                && appointmentRepository
                .findByDateAndTimeAndPatientNameAndProviderName(appointmentDTO.getDate(), appointmentDTO.getTime(), appointmentDTO.getPatientName(), appointmentDTO.getProviderName())==null;
    }

}
