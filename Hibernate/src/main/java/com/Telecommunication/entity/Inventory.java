package com.Telecommunication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Correct placement of GeneratedValue annotation
    @Column(name = "inventory_id")
    private int inventoryId;

    @Column(name = "equipment_type")
    private String equipmentType;
    
    @Column(name = "quantity")
    private int quantity;

    // Getter and Setter for inventoryId
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    // Getter and Setter for equipmentType
    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    // Getter and Setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", equipmentType='" + equipmentType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
