package lucian.ehealth.entities;

import jakarta.persistence.*;
import lucian.ehealth.dto.PharmacyInventoryDTO;

import java.time.LocalDate;

@Entity
@Table(name = "PHARMACY_INVENTORY")
public class PharmacyInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemID;
    private String itemName;
    private String providerFullName;
    private String category; // e.g., prescription drugs, over-the-counter medications, medical supplies).
    private int quantity;
    private double unitPrice;
    private String manufacturer;
    private LocalDate expirationDate;
    private String batchNumber; // A unique identifier for a batch of items from the same manufacturer.
    private int stockLevel; // The minimum quantity threshold that triggers the need for restocking.
    private String storageConditions;
    private String notes;

    public PharmacyInventory(){}

    public PharmacyInventory(PharmacyInventoryDTO pharmacyInventoryDTO){
        itemID = pharmacyInventoryDTO.getItemID();
        itemName = pharmacyInventoryDTO.getItemName();
        providerFullName = pharmacyInventoryDTO.getProviderFullName();
        category = pharmacyInventoryDTO.getCategory();
        quantity = pharmacyInventoryDTO.getQuantity();
        unitPrice = pharmacyInventoryDTO.getUnitPrice();
        manufacturer = pharmacyInventoryDTO.getManufacturer();
        expirationDate = pharmacyInventoryDTO.getExpirationDate();
        batchNumber = pharmacyInventoryDTO.getBatchNumber();
        stockLevel = pharmacyInventoryDTO.getStockLevel();
        storageConditions = pharmacyInventoryDTO.getStorageConditions();
        notes = pharmacyInventoryDTO.getNotes();
    }

    @Override
    public String toString() {
        return "PharmacyInventory{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", providerFullName='" + providerFullName + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", manufacturer='" + manufacturer + '\'' +
                ", expirationDate=" + expirationDate +
                ", batchNumber='" + batchNumber + '\'' +
                ", stockLevel=" + stockLevel +
                ", storageConditions='" + storageConditions + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProviderFullName() {
        return providerFullName;
    }

    public void setProviderFullName(String providerFullName) {
        this.providerFullName = providerFullName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String getStorageConditions() {
        return storageConditions;
    }

    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
