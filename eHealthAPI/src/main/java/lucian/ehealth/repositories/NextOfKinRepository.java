package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.NextOfKin;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NextOfKinRepository extends CrudRepository<NextOfKin, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    NextOfKin findByFullName(String fullName);

}
