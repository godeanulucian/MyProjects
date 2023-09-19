package lucian.ehealth.repositories;

import jakarta.persistence.LockModeType;
import lucian.ehealth.entities.User;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByUsername(String username);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByCardNumber(String cardNumber);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByUsernameAndCardNumber(String username, String cardNumber);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByFullName(String fullName);

}
