package lucian.ehealth.services;

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
        providerRepository.save(provider);
        ProviderDTO response = new ProviderDTO(provider);

        providerDTO.setReturnCode("\nprovider created successfully\n");
        //System.out.println(providerDTO.getReturnCode());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}
