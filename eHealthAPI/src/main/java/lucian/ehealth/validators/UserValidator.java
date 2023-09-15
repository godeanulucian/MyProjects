package lucian.ehealth.validators;

import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;
    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,128}$";
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    public boolean matcher(UserDTO userDTO) {
        return userDTO != null
                && userDTO.getUsername().matches("[a-z0-9._]{3,25}+")
                && userDTO.getPassword().matches(passwordRegex)
                && userDTO.getEmail().matches(emailRegex)
                && userDTO.getContactInformation().length() <= 300
                && userDTO.getAddress().matches("[a-zA-Z0-9 ,.-]{5,100}+")
                && userDTO.getCardNumber().matches("[0-9]{16}+")
                && userDTO.getAmount() >= 0;
    }
    public boolean isNotNull(UserDTO userDTO) {
        return userDTO != null
                && userDTO.getUsername() != null
                && userDTO.getPassword() != null
                && userDTO.getEmail() != null
                && userDTO.getContactInformation() != null
                && userDTO.getAddress() != null
                && userDTO.isDoctor() != null // && (userDTO.isDoctor() || !userDTO.isDoctor()) // in case isDoctor is not bool checked before null check
                && userDTO.getCardNumber() != null
                && userDTO.getAmount() != null
                && userDTO.getGender() != null
                && userDTO.getDateOfBirth() != null
                && userDTO.getFullName() != null;
    }
    public boolean validateUser (UserDTO userDTO){
        return isNotNull(userDTO) && matcher(userDTO)
                && userDTO.getFullName() != null && userDTO.getFullName().matches("[a-zA-Z .-]{3,128}+")
                && userDTO.getDateOfBirth() != null && userDTO.getDateOfBirth().isBefore(LocalDate.now())
                && userDTO.getGender() != null && userDTO.getGender().matches("[a-zA-Z .-]{3,10}+")

                // unique entity with unique username & unique card number - can't properly update the entity this way (works only if we update both username and card number)
                // basically a user is associated with a unique card number
                && userRepository.findByUsername(userDTO.getUsername()) == null
                && userRepository.findByCardNumber(userDTO.getCardNumber()) == null;

        // unique entity with username and card number
        // basically multiple users can use the same card number (not really safe)
        // && userRepository.findByUsernameAndCardNumber(userDTO.getUsername(), userDTO.getCardNumber())==null;
    }

    public boolean validateUpdateUser (UserDTO userDTO){
        return matcher(userDTO); // we will need to handle sql exception that is thrown in case of an existing username/card number
    }


}
