package lucian.ehealth.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "KINS")
public class NextOfKin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kinID;
    @OneToOne(mappedBy = "nextOfKin")
    public Patient patient;

}
