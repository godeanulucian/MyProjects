package lucian.ehealth.services;

import lucian.ehealth.dto.PatientDTO;
import lucian.ehealth.repositories.PatientRepository;
import lucian.ehealth.validators.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientDTO patientDTO;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientValidator patientValidator;
    /*private static ArrayList<PatientDTO> patients = new ArrayList<PatientDTO>(Arrays.asList(
            new PatientDTO(1, "Irina Crupoveatchin", LocalDate.parse("12-12-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "female")
    ));*/

    public ResponseEntity<?> getAllPatients() {
        System.out.println("\n--- Patients List ---\n");
        //patients.stream().forEach(System.out::println);

        return new ResponseEntity<>(patientRepository.findAll(), HttpStatus.OK);
        // return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
