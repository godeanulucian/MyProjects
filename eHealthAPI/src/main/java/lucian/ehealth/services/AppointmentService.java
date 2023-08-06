package lucian.ehealth.services;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    AppointmentDTO appointmentDTO;
    @Autowired
    AppointmentRepository appointmentRepository;

    public ResponseEntity<?> getAllAppointments() {
        System.out.println("\n--- Appointments List ---\n");
        return new ResponseEntity<>(appointmentRepository.findAll(), HttpStatus.OK);
    }


}
