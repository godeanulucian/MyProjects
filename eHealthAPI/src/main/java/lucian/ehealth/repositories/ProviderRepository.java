package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.Provider;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Provider findByFullName(String fullName);

}
