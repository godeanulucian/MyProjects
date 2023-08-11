package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.Payment;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Payment findByPaymentID(Long paymentID);

}
