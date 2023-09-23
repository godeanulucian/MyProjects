package lucian.ehealth.validators;

import lucian.ehealth.dto.ProviderDTO;
import lucian.ehealth.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderValidator {

    @Autowired
    ProviderRepository providerRepository;
    // https://madph.mylicense.com/eGov/custom/LN%20Formats.htm
    String licenseRegex = "^(DN|DH|DL|DF|DE|RN|LN|NH|NHT|PL|PP|CC|PH|PT|PN|PI|DS|WD|NU|BR|CF|CS|PA|PAT|RT|RL)\\d{4}(L|A|B|C|D)?$";

    public boolean matcher(ProviderDTO providerDTO) {
        return providerDTO!=null
                && providerDTO.getFullName().matches("[a-zA-Z .-]{3,128}+")
                && providerDTO.getSpecialization().matches("[a-zA-Z .-]{3,128}+")
                && providerDTO.getAddress().matches("[a-zA-Z0-9 ,.-]{5,100}+")
                && providerDTO.getContactInformation().length() <= 300
                && providerDTO.getServices().length() <= 300
                && providerDTO.getLicenseNumber().matches(licenseRegex)
                && providerDTO.getLanguage().matches("[a-zA-Z ,.-]{3,128}+")
                && (providerDTO.getAverageRating() >= 0.0 && providerDTO.getAverageRating() <= 5.0)
                && providerDTO.isAcceptsInsurance()!=null
                && providerDTO.getAcceptedInsurancePlans().matches("[a-zA-Z0-9 ,.-]{5,100}+")
                && providerDTO.isHasPharmacyInventory()!=null
                && providerDTO.isAvailable()!=null
                && providerDTO.isHasAppointment()!=null
                && providerDTO.isHasPayment()!=null;
    }

    public boolean validateProvider(ProviderDTO providerDTO) {
        return matcher(providerDTO)
                // unique providerID
                && providerRepository.findByProviderID(providerDTO.getProviderID())==null;
    }

    public boolean validateUpdateProvider(ProviderDTO providerDTO) {
        return matcher(providerDTO);
    }

}
