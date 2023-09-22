package lucian.ehealth.validators;

import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class PrescriptionValidator {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    public boolean matcher(PrescriptionDTO prescriptionDTO) {
        return prescriptionDTO!=null
                && prescriptionDTO.getPrescriptionName().length() <= 128
                && prescriptionDTO.getPatientFullName().matches("[a-zA-Z .-]{3,128}+")
                && prescriptionDTO.getProviderFullName().matches("[a-zA-Z .-]{3,128}+")
                && prescriptionDTO.getMedication().length() <= 128
                && prescriptionDTO.getRefills() >= 0
                && prescriptionDTO.getPharmacy().matches("[a-zA-Z .-]{3,128}+")
                && prescriptionDTO.getInstructions().length() <= 128;
    }

    public boolean validatePrescription(PrescriptionDTO prescriptionDTO) {
        return matcher(prescriptionDTO)
                // unique prescription ID
                && prescriptionRepository.findByPrescriptionID(prescriptionDTO.getPrescriptionID())==null;
    }

    public boolean validateUpdatePrescription(PrescriptionDTO prescriptionDTO) {
        return matcher(prescriptionDTO);
    }

}
