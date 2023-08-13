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
        return appointmentDTO!=null
                && appointmentDTO.getDate()!=null
                && appointmentDTO.getTime()!=null
                && appointmentDTO.getPatientName()!=null
                && appointmentDTO.getProviderName()!=null
                && appointmentRepository.findByAppointmentID(appointmentDTO.getAppointmentID())==null;
    }

}
