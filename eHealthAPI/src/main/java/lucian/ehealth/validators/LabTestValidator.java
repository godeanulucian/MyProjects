package lucian.ehealth.validators;

import lucian.ehealth.dto.InsuranceDTO;
import lucian.ehealth.dto.LabTestDTO;
import lucian.ehealth.repositories.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabTestValidator {

    @Autowired
    LabTestRepository labTestRepository;

    public boolean matcher(LabTestDTO labTestDTO) {
        return labTestDTO!=null
                && labTestDTO.getTestName().length() <= 128
                && labTestDTO.getPatientFullName().matches("[a-zA-Z .-]{3,128}+")
                && labTestDTO.getType().length() <= 128
                && labTestDTO.getResult().length() <= 300
                && labTestDTO.getTechnician().matches("[a-zA-Z .-]{3,128}+")
                && labTestDTO.getLocation().length() <= 100
                && labTestDTO.getComments().length() <= 128;
    }
    public boolean validateLabTest(LabTestDTO labTestDTO) {
        return matcher(labTestDTO)
                // unique lab test by UUID
                && labTestRepository.findByLabTestID(labTestDTO.getLabTestID())==null;
    }
    public boolean validateUpdateLabTest (LabTestDTO labTestDTO){
        return matcher(labTestDTO);
    }

}
