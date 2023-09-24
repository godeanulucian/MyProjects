package lucian.ehealth.controllers;

import lucian.ehealth.dto.PrescriptionDTO;
import lucian.ehealth.dto.ProviderDTO;
import lucian.ehealth.dto.UserDTO;
import lucian.ehealth.handlers.RequestHandler;
import lucian.ehealth.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/providers")
public class ProviderController {

    @Autowired
    ProviderService providerService;
    @Autowired
    RequestHandler requestHandler;

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllProviders() {
        return requestHandler.handleUnauthorizedRequest(new ProviderDTO());

        // return providerService.getAllProviders();
    }

    // AUTO CREATE
    @PostMapping
    public ResponseEntity<?> addProvider(@RequestBody UserDTO userDTO) {
        return providerService.fetchDataFromUser(userDTO);
    }

    // READ
    @GetMapping(path = "/{fullName}")
    public ResponseEntity<?> getProvider(@PathVariable String fullName) {
        return providerService.getProvider(fullName);
    }

    // UPDATE
    @PutMapping(path = "/{fullName}")
    public ResponseEntity<?> updateProvider(@RequestBody ProviderDTO providerDTO, @PathVariable String fullName) {
        return providerService.updateProvider(providerDTO, fullName);
    }

}
