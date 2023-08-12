package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.Insurance;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InsuranceRepository extends CrudRepository<Insurance, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Insurance findByCompanyName(String companyName);

}
