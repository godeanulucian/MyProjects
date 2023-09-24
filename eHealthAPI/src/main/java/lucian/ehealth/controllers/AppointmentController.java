package lucian.ehealth.controllers;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.dto.PaymentDTO;
import lucian.ehealth.handlers.RequestHandler;
import lucian.ehealth.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        return requestHandler.handleUnauthorizedRequest(new AppointmentDTO());

        // return appointmentService.getAllAppointments();
    }

    // CREATE
    /*@PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.addAppointment(appointmentDTO);
    }*/

    // READ
    @GetMapping(path = "/{appointmentID}")
    public ResponseEntity<?> getAppointment(@PathVariable Long appointmentID){
        return appointmentService.getAppointment(appointmentID);
    }

    // UPDATE
    @PutMapping(path = "/{appointmentID}")
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDTO appointmentDTO, @PathVariable Long appointmentID) {
        return appointmentService.updateAppointment(appointmentDTO, appointmentID);
    }

    // DELETE
    @DeleteMapping(path = "/{appointmentID}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentID) {
        return appointmentService.deleteAppointment(appointmentID);
    }

    // CREATE - BOOK AN APPOINTMENT
    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.bookAppointment(appointmentDTO);
    }

}
