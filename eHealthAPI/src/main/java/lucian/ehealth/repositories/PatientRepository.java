package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.Patient;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends CrudRepository<Patient, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Patient findByFullName(String fullName);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Patient findByPatientID(Long patientID);
}
