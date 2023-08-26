package lucian.ehealth.controllers;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        AppointmentDTO response = new AppointmentDTO();
        response.setReturnCode("You are not allowed to do this");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);

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
