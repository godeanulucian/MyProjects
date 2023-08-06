package lucian.ehealth.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PHARMACY_INVENTORY")
public class PharmacyInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemID;
    public String itemName;
    @OneToOne(mappedBy = "pharmacyInventory")
    private Provider provider;
    public String category; // e.g., prescription drugs, over-the-counter medications, medical supplies).
    public int quantity;
    public double unitPrice;
    public String manufacturer;
    public LocalDate expirationDate;
    public String batchNumber; // A unique identifier for a batch of items from the same manufacturer.
    public int stockLevel; // The minimum quantity threshold that triggers the need for restocking.
    public String storageConditions;
    public String notes;


}
