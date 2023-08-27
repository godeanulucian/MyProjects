package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.LabTest;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LabTestRepository extends CrudRepository<LabTest, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    LabTest findByLabTestID(Long labTestID);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    LabTest findByPatientFullName(String patientFullName);

}
