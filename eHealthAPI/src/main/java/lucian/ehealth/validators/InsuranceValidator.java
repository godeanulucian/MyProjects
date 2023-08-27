package lucian.ehealth.validators;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsuranceValidator {

    @Autowired
    InsuranceRepository insuranceRepository;

    public boolean validateInsurance(InsuranceDTO insuranceDTO){
        return insuranceDTO!=null
                && insuranceDTO.getPatientFullName()!=null && insuranceDTO.getPatientFullName().matches("[a-zA-Z .-]+")
                && insuranceDTO.getCompanyName()!=null && insuranceDTO.getCompanyName().matches("[a-zA-Z .-]+")
                && insuranceDTO.getStartDate()!=null
                && insuranceDTO.getEndDate()!=null
                && insuranceDTO.getCoveragePercent()!=null
                && insuranceDTO.getContactInformation()!=null
                // unique insurance by patient name
                && insuranceRepository.findByInsuranceID(insuranceDTO.getInsuranceID())==null;
    }

}
