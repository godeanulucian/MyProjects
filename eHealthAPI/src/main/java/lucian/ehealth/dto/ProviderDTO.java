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
    public String fullName; // Name of the health provider
    public String specialization; // The area of medical specialization (e.g., General Practitioner, Cardiologist)
    public String address; // Address of the health provider's clinic or hospital
    public String contactInformation; // Contact number to reach the health provider
    public String services; // List of medical services offered by the health provider
    public String licenseNumber; // License number of the health provider
    public String language; // List of languages spoken by the health provider
    public double averageRating; // Average rating based on patient feedback
    public boolean acceptsInsurance; // Indicates if the health provider accepts insurance
    public String acceptedInsurancePlans; // List of accepted insurance plans
    private PharmacyInventory pharmacyInventory;
    public boolean isAvailable;
    public Appointment appointment;
    private Payment payment;
    private String returnCode;

    public ProviderDTO() {}

    public ProviderDTO(Provider provider) {
        providerID = provider.getproviderID();
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
        pharmacyInventory = provider.getPharmacyInventory();
        isAvailable = provider.isAvailable;
        appointment = provider.getAppointment();
        payment = provider.getPayment();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "ProviderDTO{" +
                "providerID=" + providerID +
                ", fullName='" + fullName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", address='" + address + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", services=" + services +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", language=" + language +
                ", averageRating=" + averageRating +
                ", acceptsInsurance=" + acceptsInsurance +
                ", acceptedInsurancePlans=" + acceptedInsurancePlans +
                ", pharmacyInventory=" + pharmacyInventory +
                ", isAvailable=" + isAvailable +
                ", appointment=" + appointment +
                ", payment=" + payment +
                ", returnCode='" + returnCode + '\'' +
                '}';
    }

    public Long getproviderID() {
        return providerID;
    }

    public void setproviderID(Long providerID) {
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

    public PharmacyInventory getPharmacyInventory() {
        return pharmacyInventory;
    }

    public void setPharmacyInventory(PharmacyInventory pharmacyInventory) {
        this.pharmacyInventory = pharmacyInventory;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
