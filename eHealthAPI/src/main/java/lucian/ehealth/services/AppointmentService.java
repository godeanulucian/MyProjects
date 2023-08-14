package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.entities.Appointment;
import lucian.ehealth.repositories.AppointmentRepository;
import lucian.ehealth.validators.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// transaction is a sequence of multiple operations performed on a database
// The @Transactional annotation is metadata that specifies that an interface, class, or method must have transactional semantics (for example, "start a brand new read-only transaction when this method is invoked, suspending any existing transaction").
@Transactional
@Service
public class AppointmentService {

    @Autowired
    AppointmentDTO appointmentDTO;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    AppointmentValidator appointmentValidator;

    // READ ALL
    public ResponseEntity<?> getAllAppointments() {
        // fetch all entities into a list using forEach
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentRepository.findAll().forEach(appointmentList::add);
        if(!appointmentList.isEmpty())
            return new ResponseEntity<>(appointmentRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
        else {
            AppointmentDTO response = new AppointmentDTO();
            response.setReturnCode("No appointments scheduled");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    // CREATE
    public ResponseEntity<?> addAppointment(AppointmentDTO appointmentDTO) {
        // validator test
        if (appointmentValidator.validateAppointment(appointmentDTO)) {
            // I. create entity object so we can perform db operations on it
            Appointment appointment = new Appointment(appointmentDTO);
            // II. save the new entity into a repository
            // III. create a HTTP response using a DTO object
            AppointmentDTO response = new AppointmentDTO(appointmentRepository.save(appointment));

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            // if action is not valid create HTTP response using an empty DTO object where we only set the returnCode
            AppointmentDTO response =new AppointmentDTO();
            response.setReturnCode("Appointment was not created");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    // READ
    public ResponseEntity<?> getAppointment(Long appointmentID) {
        Appointment appointment = appointmentRepository.findByAppointmentID(appointmentID);
        if (appointment!=null)
            return new ResponseEntity<>(new AppointmentDTO(appointment), new HttpHeaders(), HttpStatus.OK);
        else {
            AppointmentDTO response = new AppointmentDTO();
            response.setReturnCode("Appointment not found");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    // UPDATE
    public ResponseEntity<?> updateAppointment(AppointmentDTO appointmentDTO, Long appointmentID){
        Appointment appointment = appointmentRepository.findByAppointmentID(appointmentID);
        if (appointment!=null && appointmentValidator.validateAppointment(appointmentDTO)) {
            appointment.setDate(appointmentDTO.getDate());
            appointment.setTime(appointmentDTO.getTime());
            appointment.setPatientName(appointmentDTO.getPatientName());
            appointment.setProviderName(appointmentDTO.getProviderName());
            appointment.setType(appointmentDTO.getType());
            appointment.setStatus(appointmentDTO.getStatus());
            appointment.setReason(appointmentDTO.getReason());
            appointment.setLocation(appointmentDTO.getLocation());
            appointment.setNotes(appointmentDTO.getNotes());

            AppointmentDTO response = new AppointmentDTO(appointmentRepository.save(appointment));
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            AppointmentDTO response = new AppointmentDTO();
            response.setReturnCode("Appointment not found or update error");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE
    public ResponseEntity<?> deleteAppointment(Long appointmentID) {
        Appointment appointment = appointmentRepository.findByAppointmentID(appointmentID);
        if (appointment!=null) {
            appointmentRepository.deleteById(appointmentID);
            // appointmentRepository.deleteByAppointmentID(appointmentID);
            AppointmentDTO response = new AppointmentDTO();
            response.setReturnCode("Appointment deleted");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            AppointmentDTO response = new AppointmentDTO();
            response.setReturnCode("Appointment not found");

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

}
