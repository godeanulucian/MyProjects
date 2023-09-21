package lucian.ehealth.controllers;

import lucian.ehealth.dto.ProviderDTO;
import lucian.ehealth.dto.UserDTO;
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

    // UNAUTHORIZED REQUEST HANDLER
    public ResponseEntity<?> handleUnauthorizedRequest() {
        ProviderDTO response = new ProviderDTO();
        response.setReturnCode("You are not allowed to do this");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllProviders() {
        // return providerService.getAllProviders();

        return handleUnauthorizedRequest();
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
