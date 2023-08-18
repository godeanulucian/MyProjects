package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.Appointment;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    // define personalized crud methods by extending the crud repo
    // holding PESSIMISTIC_WRITE lock will prevent other transactions from reading, updating or deleting the data
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Appointment findByAppointmentID(Long appointmentID);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Appointment findByDateAndTimeAndProviderName(LocalDate date, LocalTime time, String providerName);

    /*@Lock(LockModeType.PESSIMISTIC_WRITE)
    Appointment deleteByAppointmentID(Long appointmentID);*/
}
