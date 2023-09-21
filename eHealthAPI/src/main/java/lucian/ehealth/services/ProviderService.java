package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.ProviderDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.Provider;
import lucian.ehealth.repositories.ProviderRepository;
import lucian.ehealth.validators.ProviderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProviderService {

    @Autowired
    ProviderDTO providerDTO;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ProviderValidator providerValidator;

    // BAD REQUEST HANDLER
    public ResponseEntity<?> handleBadRequest(String returnCode) {
        ProviderDTO response = new ProviderDTO();
        response.setReturnCode(returnCode);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // READ ALL
    public ResponseEntity<?> getAllProviders() {
        List<Provider> providers = new ArrayList<>();
        providerRepository.findAll().forEach(providers::add);
        if (!providers.isEmpty()) {
            return new ResponseEntity<>(providers, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("No providers found");
        }
    }

    // AUTO CREATE
    public ResponseEntity<?> fetchDataFromUser(UserDTO userDTO) {
        Provider provider = new Provider(providerDTO);
        provider.setFullName(userDTO.getFullName());
        provider.setContactInformation(userDTO.getContactInformation());
        provider.setAddress(userDTO.getAddress());

        ProviderDTO response = new ProviderDTO(providerRepository.save(provider));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    // READ
    public ResponseEntity<?> getProvider(String fullName) {
        Provider provider = providerRepository.findByFullName(fullName);
        if(provider!=null) {
            ProviderDTO response = new ProviderDTO(provider);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Provider not found");
        }
    }

    // UPDATE
    public ResponseEntity<?> updateProvider(ProviderDTO providerDTO, String fullName) {
        Provider provider = providerRepository.findByFullName(fullName);
        if (provider!=null && providerValidator.validateUpdateProvider(providerDTO)) {
            provider.setSpecialization(providerDTO.getSpecialization());
            provider.setServices(providerDTO.getServices());
            provider.setLicenseNumber(providerDTO.getLicenseNumber());
            provider.setLanguage(providerDTO.getLanguage());
            provider.setAverageRating(providerDTO.getAverageRating());
            provider.setAcceptsInsurance(providerDTO.isAcceptsInsurance());
            provider.setAcceptedInsurancePlans(providerDTO.getAcceptedInsurancePlans());
            provider.setAvailable(providerDTO.isAvailable());

            ProviderDTO response = new ProviderDTO(providerRepository.save(provider));
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return handleBadRequest("Provider not found or update error");
        }
    }

    // DELETE
    // is not necessary to create a new service because we can use the delete method from user service

}
