package lucian.ehealth.validators;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.dto.PatientDTO;
import lucian.ehealth.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientValidator {

    @Autowired
    PatientRepository patientRepository;
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    public boolean isNotNull(PatientDTO patientDTO) {
        return patientDTO!=null
                && patientDTO.getFullName()!=null
                && patientDTO.getDateOfBirth()!=null
                && patientDTO.getGender()!=null
                && patientDTO.getEmail()!=null
                && patientDTO.getPhoneNumber()!=null
                && patientDTO.getSocialMedia()!=null
                && patientDTO.getAddress()!=null
                && patientDTO.isHasInsurance()!=null
                && patientDTO.getEmergencyContact()!=null
                && patientDTO.getBloodType()!=null
                && patientDTO.getHeight()!=null
                && patientDTO.getWeight()!=null
                && patientDTO.getLanguage()!=null
                && patientDTO.getPrimaryCarePhysician()!=null
                && patientDTO.getAllergies()!=null
                && patientDTO.getMedications()!=null
                && patientDTO.getNextOfKinFullName()!=null
                && patientDTO.getTestName()!=null
                && patientDTO.getPrescriptionName()!=null
                && patientDTO.isHasAppointment()!=null
                && patientDTO.isHasPayment()!=null;
    }
    public boolean matcher(PatientDTO patientDTO) {
        return patientDTO!=null
                && patientDTO.getFullName().matches("[a-zA-Z .-]{3,128}+")
                && patientDTO.getDateOfBirth().isBefore(LocalDate.now())
                && patientDTO.getGender().matches("[a-zA-Z .-]{3,10}+")
                && patientDTO.getEmail().matches(emailRegex)
                && patientDTO.getPhoneNumber().matches("[0-9+]{15}+")
                && patientDTO.getSocialMedia().matches("[a-z0-9.,_-]{3,200}+")
                && patientDTO.getAddress().matches("[a-zA-Z0-9 ,.-]{5,100}+")
                && patientDTO.getEmergencyContact().length() <= 300
                && patientDTO.getBloodType().matches("[ABO]{1,2}+")
                // should add Rh+/- field
                && (patientDTO.getHeight() >= 0 && patientDTO.getHeight() <= 270) // centimeters
                && (patientDTO.getWeight() >= 0 && patientDTO.getWeight() <= 635) // kilograms
                && patientDTO.getLanguage().matches("[a-zA-Z ,.-]{3,128}+")
                && patientDTO.getPrimaryCarePhysician().matches("[a-zA-Z .-]{3,128}+")
                && patientDTO.getAllergies().length() <= 300
                && patientDTO.getMedications().length() <= 3
                && patientDTO.getNextOfKinFullName().matches("[a-zA-Z .-]{3,128}+")
                && patientDTO.getTestName().length() <= 128
                && patientDTO.getPrescriptionName().length() <= 128;
    }
    public boolean validatePatient(PatientDTO patientDTO) {
        return isNotNull(patientDTO) && matcher(patientDTO)
                // unique patientID
                && patientRepository.findByPatientID(patientDTO.getPatientID())==null;
    }
    public boolean validateUpdatePatient (PatientDTO patientDTO){
        return matcher(patientDTO);
    }

}
