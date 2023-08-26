package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.entities.Appointment;
import lucian.ehealth.entities.Patient;
import lucian.ehealth.entities.Provider;
import lucian.ehealth.repositories.AppointmentRepository;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.repositories.ProviderRepository;
import lucian.ehealth.validators.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ProviderRepository providerRepository;

    // BAD REQUEST HANDLER
    public ResponseEntity<?> handleBadRequest(String returnCode) {
        // if action is not valid create HTTP response using an empty DTO object where we only set the returnCode
        AppointmentDTO response = new AppointmentDTO();
        response.setReturnCode(returnCode);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // READ ALL
    public ResponseEntity<?> getAllAppointments() {
        if(appointmentRepository.findAll()!=null)
            return new ResponseEntity<>(appointmentRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
        else {
            return handleBadRequest("No appointments scheduled");
        }
    }

    // CREATE
    public ResponseEntity<?> addAppointment(AppointmentDTO appointmentDTO) {
        // validator test
        if (appointmentValidator.validateAppointment(appointmentDTO)) {
            // I. create entity object, so we can perform db operations on it
            Appointment appointment = new Appointment(appointmentDTO);
            appointment.setStatus("Scheduled");
            // II. save the new entity into a repository
            // III. create a HTTP response using a DTO object
            AppointmentDTO response = new AppointmentDTO(appointmentRepository.save(appointment));
            // response.setStatus("Scheduled");

            // after appointment ic created we need to update hasAppointment to "true"
            Patient patient = patientRepository.findByFullName(appointmentDTO.getPatientName());
            Provider provider = providerRepository.findByFullName(appointmentDTO.getProviderName());
            updatePatientAndProvider(patient, provider, true);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } else {
            // if action is not valid create HTTP response using an empty DTO object where we only set the returnCode
            return handleBadRequest("Appointment was not created");
        }
    }

    // READ
    public ResponseEntity<?> getAppointment(Long appointmentID) {
        Appointment appointment = appointmentRepository.findByAppointmentID(appointmentID);
        if (appointment != null)
            return new ResponseEntity<>(new AppointmentDTO(appointment), new HttpHeaders(), HttpStatus.OK);
        else {
            return handleBadRequest("Appointment not found");
        }
    }

    // UPDATE
    public ResponseEntity<?> updateAppointment(AppointmentDTO appointmentDTO, Long appointmentID) {
        Appointment appointment = appointmentRepository.findByAppointmentID(appointmentID);
        if (appointment != null && appointmentValidator.validateAppointment(appointmentDTO)) {
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
        } else {
            return handleBadRequest("Appointment not found or update error");
        }
    }

    // DELETE
    public ResponseEntity<?> deleteAppointment(Long appointmentID) {
        Appointment appointment = appointmentRepository.findByAppointmentID(appointmentID);
        if (appointment != null) {
            appointmentRepository.deleteById(appointmentID);
            AppointmentDTO response = new AppointmentDTO();
            response.setReturnCode("Appointment deleted");

            Patient patient = patientRepository.findByFullName(appointment.getPatientName());
            Provider provider = providerRepository.findByFullName(appointment.getProviderName());
            updatePatientAndProvider(patient, provider, false);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } else {
            return handleBadRequest("Appointment not found");
        }
    }

    // BOOK AN APPOINTMENT
    public ResponseEntity<?> bookAppointment(AppointmentDTO appointmentDTO) {
        // declare our entities that we will update after booking an appointment
        Patient patient = patientRepository.findByFullName(appointmentDTO.getPatientName());
        Provider provider = providerRepository.findByFullName(appointmentDTO.getProviderName());

        // validate our appointment (see AppointmentValidator class)
        if (appointmentValidator.validateAppointment(appointmentDTO) && appointmentValidator.validateBookAppointment(appointmentDTO)) {
            // if appointment is valid, we save it to our repository with status "Scheduled"
            Appointment appointment = new Appointment(appointmentDTO);
            appointment.setStatus("Scheduled");
            // create HTTP response
            AppointmentDTO response = new AppointmentDTO(appointmentRepository.save(appointment));
            // notify Patient and Provider
            updatePatientAndProvider(patient, provider, true);

            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Appointment was not booked");
        }
    }

    private void updatePatientAndProvider(Patient patient, Provider provider, boolean checker) {
        patient.setHasAppointment(checker);
        provider.setHasAppointment(checker);
    }
}
