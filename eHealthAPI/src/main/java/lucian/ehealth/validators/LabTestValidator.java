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
                && labTestDTO.getPatientFullName()!=null && labTestDTO.getPatientFullName().matches("[a-zA-Z .-]+")
                && labTestDTO.getType()!=null
                && labTestDTO.getTestDate()!=null
                && labTestDTO.getResult()!=null
                && labTestDTO.getTechnician()!=null && labTestDTO.getTechnician().matches("[a-zA-Z .-]+")
                && labTestDTO.getLocation()!=null
                && labTestDTO.getComments()!=null
                // unique lab test by UUID
                && labTestRepository.findByLabTestID(labTestDTO.getLabTestID())==null;
    }

}
