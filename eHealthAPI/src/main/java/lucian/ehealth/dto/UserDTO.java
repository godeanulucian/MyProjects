package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.User;
import lucian.ehealth.handlers.RequestHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements RequestHandler.ReturnCodeAware {
    private Long userID;
    private String username;
    private String password;
    private String email;
    private String contactInformation;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private Boolean isDoctor;
    private String cardNumber;
    private Double amount;
    private String returnCode;

    public UserDTO() {}

    public UserDTO(User user) {
        userID = user.getUserID();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        contactInformation = user.getContactInformation();
        fullName = user.getFullName();
        dateOfBirth = user.getDateOfBirth();
        gender = user.getGender();
        address = user.getAddress();
        isDoctor = user.isDoctor();
        cardNumber = user.getCardNumber();
        amount = user.getAmount();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", isDoctor=" + isDoctor +
                ", cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                ", returnCode='" + returnCode + '\'' +
                '}';
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long id) {
        this.userID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(Boolean doctor) {
        isDoctor = doctor;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
