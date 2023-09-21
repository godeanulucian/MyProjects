package lucian.ehealth.dto;

import lucian.ehealth.entities.PharmacyInventory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class PharmacyInventoryDTO {
    private Long itemID;
    private String itemName;
    private String providerFullName;
    private String category; // e.g., prescription drugs, over-the-counter medications, medical supplies).
    private Integer quantity;
    private Double unitPrice;
    private String manufacturer;
    private LocalDate expirationDate;
    private String batchNumber; // A unique identifier for a batch of items from the same manufacturer.
    private Integer stockLevel; // The minimum quantity threshold that triggers the need for restocking.
    private String storageConditions;
    private String notes;
    private String returnCode;

    public PharmacyInventoryDTO(){}

    public PharmacyInventoryDTO(PharmacyInventory pharmacyInventory){
        itemID = pharmacyInventory.getItemID();
        itemName = pharmacyInventory.getItemName();
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
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

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
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
