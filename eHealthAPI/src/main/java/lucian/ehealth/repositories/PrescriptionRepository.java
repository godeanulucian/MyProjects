package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.Prescription;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Prescription findByPrescriptionName(String prescriptionName);
}
