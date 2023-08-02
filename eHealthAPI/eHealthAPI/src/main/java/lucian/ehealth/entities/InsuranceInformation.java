package lucian.ehealth.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Component;

// @Component
// @Embeddable
@Entity
public class InsuranceInformation {
    @Id
    @OneToOne(mappedBy = "insuranceInformation")
    public Patient patient;

}
