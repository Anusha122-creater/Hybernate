package com.Telecommunication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "network_element")
public class NetworkElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Correctly placed above the @Column annotation
    @Column(name = "network_element_id")
    private int networkElementId;

    @Column(name = "type")
    private String type;

    @Column(name = "location")
    private String location;

    // Getter and Setter methods
    public int getNetworkElementId() {
        return networkElementId;
    }

    public void setNetworkElementId(int networkElementId) {
        this.networkElementId = networkElementId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "NetworkElement{" +
                "networkElementId=" + networkElementId +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
