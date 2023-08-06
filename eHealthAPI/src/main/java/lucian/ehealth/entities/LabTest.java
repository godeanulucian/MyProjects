package lucian.ehealth.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "LAB_TESTS")
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labTestID;
    @OneToOne(mappedBy = "labTest")
    public Patient patient;

}
