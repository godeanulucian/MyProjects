package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.UserDTO;

import java.time.LocalDate;
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    public String username;
    private String password;
    private String email;
    public String contactInformation;

    public String fullName;
    public LocalDate dateOfBirth;
    public String gender;
    public String address;
    public boolean isDoctor;

    public User() {}
    public User(UserDTO userDTO) {
        userID = userDTO.getUserID();
        username = userDTO.getUsername();
        password = userDTO.getPassword();
        email = userDTO.getPassword();
        contactInformation = userDTO.getContactInformation();
        fullName = userDTO.getFullName();
        dateOfBirth = userDTO.getDateOfBirth();
        gender = userDTO.getGender();
        address = userDTO.getAddress();
        isDoctor = userDTO.isDoctor();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", isDoctor=" + isDoctor +
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

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
}
