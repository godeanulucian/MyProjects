package lucian.ehealth.controllers;

import lucian.ehealth.dto.AppointmentDTO;
import lucian.ehealth.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return appointmentService.getAllAppointments();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.addAppointment(appointmentDTO);
    }

    // READ
    @GetMapping(path = "/{appointmentID}")
    public ResponseEntity<?> getAppointment(@PathVariable Long appointmentID){
        return appointmentService.getAppointment(appointmentID);
    }
    // UPDATE
    // DELETE

}
