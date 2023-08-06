package lucian.ehealth.entities;

import java.util.List;

public class Provider {

    private Long patientID; // Unique identifier for the health provider
    public String fullName; // Name of the health provider
    public String specialization; // The area of medical specialization (e.g., General Practitioner, Cardiologist)
    public String address; // Address of the health provider's clinic or hospital
    public String contactInformation; // Contact number to reach the health provider
    public List<String> services; // List of medical services offered by the health provider
    public String licenseNumber; // License number of the health provider
    public List<String> language; // List of languages spoken by the health provider
    public double averageRating; // Average rating based on patient feedback
    public boolean acceptsInsurance; // Indicates if the health provider accepts insurance
    public List<String> acceptedInsurancePlans; // List of accepted insurance plans
    private PharmacyInventory pharmacyInventory;
    public boolean isAvailable;
    public Appointment appointment;
    private Payment payment;

}
