package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.PharmacyInventory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PharmacyInventoryRepository extends CrudRepository<PharmacyInventory, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    PharmacyInventory findByItemName(String itemName);

}
