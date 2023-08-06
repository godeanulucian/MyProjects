package lucian.ehealth.entities;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name = "PAYMENTS")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;
    @OneToOne(mappedBy = "payment")
    public Provider provider;
    @OneToOne(mappedBy = "payment")
    public Patient patient;
}
