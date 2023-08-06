package lucian.ehealth.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "INSURANCES")
public class InsuranceInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceID;
    @OneToOne(mappedBy = "insuranceInformation")
    public Patient patient;
    public String companyName; // Name of the insurance company
    public LocalDate startDate; // Start date of the insurance coverage
    public LocalDate endDate; // End date of the insurance coverage
    public double coverageAmount; // Maximum coverage amount provided by the insurance
    public String contactInformation; // Contact information for the insurance company

}
