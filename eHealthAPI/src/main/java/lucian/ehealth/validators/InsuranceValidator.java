package lucian.ehealth.validators;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InsuranceValidator {

    @Autowired
    InsuranceRepository insuranceRepository;

    public boolean validateInsurance(InsuranceDTO insuranceDTO){
        return insuranceDTO!=null
                && insuranceDTO.getPatientFullName()!=null && insuranceDTO.getPatientFullName().matches("[a-zA-Z .-]{3,128}+")
                && insuranceDTO.getCompanyName()!=null && insuranceDTO.getCompanyName().matches("[a-zA-Z .-]{3,128}+")
                && insuranceDTO.getStartDate()!=null
                && insuranceDTO.getEndDate()!=null // && insuranceDTO.getEndDate().isBefore(LocalDate.now())
                && insuranceDTO.getCoveragePercent()!=null && insuranceDTO.getCoveragePercent() >= 0
                && insuranceDTO.getContactInformation()!=null && insuranceDTO.getContactInformation().length() <= 300
                // unique insurance by patient name
                && insuranceRepository.findByInsuranceID(insuranceDTO.getInsuranceID())==null;
    }

}
