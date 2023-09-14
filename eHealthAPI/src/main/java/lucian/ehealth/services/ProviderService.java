package lucian.ehealth.services;

import jakarta.transaction.Transactional;
import lucian.ehealth.dto.ProviderDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.entities.Provider;
import lucian.ehealth.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ProviderService {

    @Autowired
    ProviderDTO providerDTO;
    @Autowired
    ProviderRepository providerRepository;

    // READ
    public ResponseEntity<?> getAllProviders() {
        System.out.println("\n--- Providers List ---\n");
        return new ResponseEntity<>(providerRepository.findAll(), HttpStatus.OK);
    }

    // AUTO CREATE PROVIDER
    public ResponseEntity<?> fetchDataFromUser(UserDTO userDTO) {
        Provider provider = new Provider(providerDTO);
        provider.setFullName(userDTO.getFullName());
        provider.setContactInformation(userDTO.getContactInformation());
        provider.setAddress(userDTO.getAddress());

        ProviderDTO response = new ProviderDTO(providerRepository.save(provider));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}
