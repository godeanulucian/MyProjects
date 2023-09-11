package lucian.ehealth.validators;

import lucian.ehealth.dto.LabTestDTO;
import lucian.ehealth.repositories.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabTestValidator {

    @Autowired
    LabTestRepository labTestRepository;

    public boolean validateLabTest(LabTestDTO labTestDTO) {
        return labTestDTO!=null
                && labTestDTO.getTestName()!=null
                && labTestDTO.getPatientFullName()!=null && labTestDTO.getPatientFullName().matches("[a-zA-Z .-]{3,128}+")
                && labTestDTO.getType()!=null && labTestDTO.getType().length() <= 128
                && labTestDTO.getTestDate()!=null
                && labTestDTO.getResult()!=null && labTestDTO.getResult().length() <= 300
                && labTestDTO.getTechnician()!=null && labTestDTO.getTechnician().matches("[a-zA-Z .-]{3,128}+")
                && labTestDTO.getLocation()!=null && labTestDTO.getLocation().length() <= 100
                && labTestDTO.getComments()!=null && labTestDTO.getComments().length() <= 128
                // unique lab test by UUID
                && labTestRepository.findByLabTestID(labTestDTO.getLabTestID())==null;
    }

}
