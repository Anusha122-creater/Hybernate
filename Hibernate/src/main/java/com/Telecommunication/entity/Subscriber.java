package com.Telecommunication.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Subscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate primary key
    @Column(name = "subscriber_id")
    private int subscriberId;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    // Self-referencing ManyToOne relationship (parent-child relationship for subscribers)
    @ManyToOne
    @JoinColumn(name = "parent_subscriber_id", referencedColumnName = "subscriber_id", nullable = true)  // Make this nullable if there’s no parent subscriber
    private Subscriber parentSubscriber;

    // One-to-many relationship with Call
    @OneToMany(mappedBy = "subscriber")
    private Set<Call> calls;

    // One-to-many relationship with Message
    @OneToMany(mappedBy = "subscriber")
    private Set<Message> messages;

    // Getters and Setters
    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subscriber getParentSubscriber() {
        return parentSubscriber;
    }

    public void setParentSubscriber(Subscriber parentSubscriber) {
        this.parentSubscriber = parentSubscriber;
    }

    public Set<Call> getCalls() {
        return calls;
    }

    public void setCalls(Set<Call> calls) {
        this.calls = calls;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    // Optional: Overriding the toString method for better representation
    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriberId=" + subscriberId +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", parentSubscriber=" + (parentSubscriber != null ? parentSubscriber.getSubscriberId() : "None") +
                '}';
    }
}
