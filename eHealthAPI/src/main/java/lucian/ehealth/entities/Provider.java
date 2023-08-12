package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.ProviderDTO;

@Entity
@Table(name = "PROVIDERS")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerID; // Unique identifier for the health provider
    private String fullName; // Name of the health provider
    private String specialization; // The area of medical specialization (e.g., General Practitioner, Cardiologist)
    private String address; // Address of the health provider's clinic or hospital
    private String contactInformation; // Contact number to reach the health provider
    private String services; // List of medical services offered by the health provider
    @Column(unique = true)
    private String licenseNumber; // License number of the health provider
    private String language; // List of languages spoken by the health provider
    private double averageRating; // Average rating based on patient feedback
    private boolean acceptsInsurance; // Indicates if the health provider accepts insurance
    private String acceptedInsurancePlans; // List of accepted insurance plans
    private boolean hasPharmacyInventory;
    private boolean isAvailable;
    private boolean hasAppointment;
    private boolean hasPayment;

    @Override
    public String toString() {
        return "Provider{" +
                "providerID=" + providerID +
                ", fullName='" + fullName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", address='" + address + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", services='" + services + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", language='" + language + '\'' +
                ", averageRating=" + averageRating +
                ", acceptsInsurance=" + acceptsInsurance +
                ", acceptedInsurancePlans='" + acceptedInsurancePlans + '\'' +
                ", hasPharmacyInventory=" + hasPharmacyInventory +
                ", isAvailable=" + isAvailable +
                ", hasAppointment=" + hasAppointment +
                ", hasPayment=" + hasPayment +
                '}';
    }

    public Provider() {}
    public Provider(ProviderDTO providerDTO) {
        providerID = providerDTO.getProviderID();
        fullName = providerDTO.getFullName();
        specialization = providerDTO.getSpecialization();
        address = providerDTO.getAddress();
        contactInformation = providerDTO.getContactInformation();
        services = providerDTO.getServices();
        licenseNumber = providerDTO.getLicenseNumber();
        language = providerDTO.getLanguage();
        averageRating = providerDTO.getAverageRating();
        acceptsInsurance = providerDTO.isAcceptsInsurance();
        acceptedInsurancePlans = providerDTO.getAcceptedInsurancePlans();
        hasPharmacyInventory = providerDTO.isHasPharmacyInventory();
        isAvailable = providerDTO.isAvailable();
        hasAppointment = providerDTO.isHasAppointment();
        hasPayment = providerDTO.isHasPayment();
    }

    public Long getProviderID() {
        return providerID;
    }

    public void setProviderID(Long providerID) {
        this.providerID = providerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isAcceptsInsurance() {
        return acceptsInsurance;
    }

    public void setAcceptsInsurance(boolean acceptsInsurance) {
        this.acceptsInsurance = acceptsInsurance;
    }

    public String getAcceptedInsurancePlans() {
        return acceptedInsurancePlans;
    }

    public void setAcceptedInsurancePlans(String acceptedInsurancePlans) {
        this.acceptedInsurancePlans = acceptedInsurancePlans;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isHasPharmacyInventory() {
        return hasPharmacyInventory;
    }

    public void setHasPharmacyInventory(boolean hasPharmacyInventory) {
        this.hasPharmacyInventory = hasPharmacyInventory;
    }

    public boolean isHasAppointment() {
        return hasAppointment;
    }

    public void setHasAppointment(boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }

    public boolean isHasPayment() {
        return hasPayment;
    }

    public void setHasPayment(boolean hasPayment) {
        this.hasPayment = hasPayment;
    }
}
