package lucian.ehealth.entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

// @Component
// @Embeddable
@Entity
@Table(name = "INSURANCES")
public class InsuranceInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long insuranceID;
    @OneToOne(mappedBy = "insuranceInformation")
    public Patient patient;

}
