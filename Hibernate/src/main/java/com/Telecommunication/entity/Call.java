package com.Telecommunication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`call_record`")  // Use backticks to escape the reserved keyword 'call'
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "call_id")
    private int callId;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id")
    private Subscriber subscriber;

    @Column(name = "call_date",columnDefinition = "DATETIME")
     // Default without precision, will use standard DATETIME
    private Date callDate;
    

    // Getters and setters
    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    @Override
    public String toString() {
        return "Call{" +
                "callId=" + callId +
                ", subscriber=" + subscriber.getSubscriberId() +
                ", callDate=" + callDate +
                '}';
    }
}
