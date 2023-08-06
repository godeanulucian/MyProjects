package lucian.ehealth.services;

import lucian.ehealth.dto.ProviderDTO;
import lucian.ehealth.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    @Autowired
    ProviderDTO providerDTO;
    @Autowired
    ProviderRepository providerRepository;
    public ResponseEntity<?> getAllProviders() {
        System.out.println("\n--- Providers List ---\n");
        return new ResponseEntity<>(providerRepository.findAll(), HttpStatus.OK);
    }

}
