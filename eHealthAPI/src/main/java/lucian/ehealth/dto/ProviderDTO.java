package lucian.ehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lucian.ehealth.entities.Appointment;
import lucian.ehealth.entities.Payment;
import lucian.ehealth.entities.PharmacyInventory;
import lucian.ehealth.entities.Provider;
import org.springframework.stereotype.Component;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {
    private Long providerID; // Unique identifier for the health provider
    private String fullName; // Name of the health provider
    private String specialization; // The area of medical specialization (e.g., General Practitioner, Cardiologist)
    private String address; // Address of the health provider's clinic or hospital
    private String contactInformation; // Contact number to reach the health provider
    private String services; // List of medical services offered by the health provider
    private String licenseNumber; // License number of the health provider
    private String language; // List of languages spoken by the health provider
    private Double averageRating; // Average rating based on patient feedback
    private Boolean acceptsInsurance; // Indicates if the health provider accepts insurance
    private String acceptedInsurancePlans; // List of accepted insurance plans
    private Boolean hasPharmacyInventory;
    private Boolean isAvailable;
    private Boolean hasAppointment;
    private Boolean hasPayment;
    private String returnCode;

    @Override
    public String toString() {
        return "ProviderDTO{" +
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
                ", returnCode='" + returnCode + '\'' +
                '}';
    }

    public ProviderDTO() {}

    public ProviderDTO(Provider provider) {
        providerID = provider.getProviderID();
        fullName = provider.getFullName();
        specialization = provider.getSpecialization();
        address = provider.getAddress();
        contactInformation = provider.getContactInformation();
        services = provider.getServices();
        licenseNumber = provider.getLicenseNumber();
        language = provider.getLanguage();
        averageRating = provider.getAverageRating();
        acceptsInsurance = provider.isAcceptsInsurance();
        acceptedInsurancePlans = provider.getAcceptedInsurancePlans();
        hasPharmacyInventory = provider.isHasPharmacyInventory();
        isAvailable = provider.isAvailable();
        hasAppointment = provider.isHasAppointment();
        hasPayment = provider.isHasPayment();
        this.returnCode = getReturnCode();
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

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Boolean isAcceptsInsurance() {
        return acceptsInsurance;
    }

    public void setAcceptsInsurance(Boolean acceptsInsurance) {
        this.acceptsInsurance = acceptsInsurance;
    }

    public String getAcceptedInsurancePlans() {
        return acceptedInsurancePlans;
    }

    public void setAcceptedInsurancePlans(String acceptedInsurancePlans) {
        this.acceptedInsurancePlans = acceptedInsurancePlans;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Boolean isHasPharmacyInventory() {
        return hasPharmacyInventory;
    }

    public void setHasPharmacyInventory(Boolean hasPharmacyInventory) {
        this.hasPharmacyInventory = hasPharmacyInventory;
    }

    public Boolean isHasAppointment() {
        return hasAppointment;
    }

    public void setHasAppointment(Boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }

    public Boolean isHasPayment() {
        return hasPayment;
    }

    public void setHasPayment(Boolean hasPayment) {
        this.hasPayment = hasPayment;
    }
}
