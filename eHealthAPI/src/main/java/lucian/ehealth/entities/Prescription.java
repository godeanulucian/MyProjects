package lucian.ehealth.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "PRESCRIPTIONS")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionID;
    @OneToOne(mappedBy = "prescription")
    public Patient patient;
}
