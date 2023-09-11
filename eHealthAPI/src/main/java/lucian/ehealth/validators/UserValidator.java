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

    public boolean validateUser(UserDTO userDTO){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,128}$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        return userDTO!=null
                && userDTO.getUsername()!=null && userDTO.getUsername().matches("[a-z0-9._]{3,25}+")
                && userDTO.getPassword()!=null && userDTO.getPassword().matches(passwordRegex)
                && userDTO.getEmail()!=null && userDTO.getEmail().matches(emailRegex)
                && userDTO.getContactInformation()!=null && userDTO.getContactInformation().length() <= 300
                && userDTO.getFullName()!=null && userDTO.getFullName().matches("[a-zA-Z .-]{3,128}+")
                && userDTO.getDateOfBirth()!=null && userDTO.getDateOfBirth().isBefore(LocalDate.now())
                && userDTO.getGender()!=null && userDTO.getGender().matches("[a-zA-Z .-]{3,10}+")
                && userDTO.getAddress()!=null && userDTO.getAddress().matches("[a-zA-Z0-9 ,.-]{5,100}+")
                && userDTO.isDoctor()!=null
                // && (userDTO.isDoctor() || !userDTO.isDoctor())
                && userDTO.getCardNumber()!=null && userDTO.getCardNumber().matches("[0-9]{16}+")
                && userDTO.getAmount()!=null && userDTO.getAmount() >= 0
                // unique username & card number
                && userRepository.findByUsername(userDTO.getUsername())==null
                && userRepository.findByCardNumber(userDTO.getCardNumber())==null;

    }

}
