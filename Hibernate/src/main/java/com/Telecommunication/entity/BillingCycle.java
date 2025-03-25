package com.Telecommunication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "billing_cycle")
public class BillingCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_cycle_id")
    private int billingCycleId;

    // Many-to-One relationship with Subscriber
    @ManyToOne
    @JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id", nullable = false)  
    private Subscriber subscriber;

 
     // Default without precision, will use standard DATETIME
 

    @Column(name = "amount")
    private Double amount;

    @Column(name = "billing_date", columnDefinition = "DATETIME")
    
    private Date billingDate;

    // Getters and setters
    public int getBillingCycleId() {
        return billingCycleId;
    }

    public void setBillingCycleId(int billingCycleId) {
        this.billingCycleId = billingCycleId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BillingCycle{" +
                "billingCycleId=" + billingCycleId +
                ", subscriber=" + (subscriber != null ? subscriber.getSubscriberId() : "None") +
                ", billingDate=" + billingDate +
                ", amount=" + amount +
                '}';
    }
}
