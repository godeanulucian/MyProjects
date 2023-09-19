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
    public boolean isNotNull(InsuranceDTO insuranceDTO) {
        return insuranceDTO!=null
                && insuranceDTO.getPatientFullName()!=null
                && insuranceDTO.getCompanyName()!=null
                && insuranceDTO.getStartDate()!=null
                && insuranceDTO.getEndDate()!=null
                && insuranceDTO.getCoveragePercent()!=null
                && insuranceDTO.getContactInformation()!=null;
    }
    public boolean matcher(InsuranceDTO insuranceDTO) {
        return insuranceDTO!=null
                && insuranceDTO.getPatientFullName().matches("[a-zA-Z .-]{3,128}+")
                && insuranceDTO.getCompanyName().matches("[a-zA-Z .-]{3,128}+")
                && insuranceDTO.getStartDate().isBefore(insuranceDTO.getEndDate())
                && insuranceDTO.getEndDate().isAfter(LocalDate.now())
                && insuranceDTO.getCoveragePercent() >= 0
                && insuranceDTO.getContactInformation().length() <= 300;
    }
    public boolean validateInsurance(InsuranceDTO insuranceDTO){
        return isNotNull(insuranceDTO) && matcher(insuranceDTO)
                // unique insurance by ID
                && insuranceRepository.findByInsuranceID(insuranceDTO.getInsuranceID())==null;
    }
    public boolean validateUpdateInsurance (InsuranceDTO insuranceDTO){
        return matcher(insuranceDTO);
    }

}
