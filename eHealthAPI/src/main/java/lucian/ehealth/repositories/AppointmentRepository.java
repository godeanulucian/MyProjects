package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.Appointment;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    // define personalized crud methods by extending the crud repo
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Appointment findByAppointmentID(Long appointmentID);
    /*@Lock(LockModeType.PESSIMISTIC_WRITE)
    Appointment deleteByAppointmentID(Long appointmentID);*/
}
