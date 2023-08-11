package lucian.ehealth.dto;

import lucian.ehealth.entities.PharmacyInventory;
import lucian.ehealth.entities.Provider;
import org.hibernate.query.sqm.produce.function.StandardFunctionReturnTypeResolvers;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class PharmacyInventoryDTO {
    private Long itemID;
    public String itemName;
    private Provider provider;
    public String providerFullName;
    public String category; // e.g., prescription drugs, over-the-counter medications, medical supplies).
    public int quantity;
    public double unitPrice;
    public String manufacturer;
    public LocalDate expirationDate;
    public String batchNumber; // A unique identifier for a batch of items from the same manufacturer.
    public int stockLevel; // The minimum quantity threshold that triggers the need for restocking.
    public String storageConditions;
    public String notes;
    private String returnCode;

    public PharmacyInventoryDTO(){}

    public PharmacyInventoryDTO(PharmacyInventory pharmacyInventory){
        itemID = pharmacyInventory.getItemID();
        itemName = pharmacyInventory.getItemName();
        provider = pharmacyInventory.getProvider();
        providerFullName = pharmacyInventory.getProviderFullName();
        category = pharmacyInventory.getCategory();
        quantity = pharmacyInventory.getQuantity();
        unitPrice = pharmacyInventory.getUnitPrice();
        manufacturer = pharmacyInventory.getManufacturer();
        expirationDate = pharmacyInventory.getExpirationDate();
        batchNumber = pharmacyInventory.getBatchNumber();
        stockLevel = pharmacyInventory.getStockLevel();
        storageConditions = pharmacyInventory.getStorageConditions();
        notes = pharmacyInventory.getNotes();
        this.returnCode = getReturnCode();
    }

    @Override
    public String toString() {
        return "PharmacyInventoryDTO{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", provider=" + provider +
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
                ", returnCode='" + returnCode + '\'' +
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
