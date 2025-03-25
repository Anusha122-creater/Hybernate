package com.Telecommunication.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageId;

    @Column(name = "message_date",columnDefinition="DATETIME")
    
    private Date messageDate;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id", nullable = false)  // Make sure this column name matches your DB column
    private Subscriber subscriber;

    // Getter and Setter methods
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageDate=" + messageDate +
                ", subscriber=" + subscriber +
                '}';
    }
}
