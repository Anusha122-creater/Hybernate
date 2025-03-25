package com.Telecommunication.serviceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Telecommunication.entity.Message;
import com.Telecommunication.entity.Subscriber;
import com.Telecommunication.service.MessageService;

public class MessageServiceImpl implements MessageService {
    Scanner sc = new Scanner(System.in);

    // Method to insert a new Message
    @Override
    public void insertMessage(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();

            Message message = new Message();

            System.out.println("Welcome to Message Management");

            // Prompt for Message ID (auto-generated)
            System.out.print("Enter Message ID: ");
            int messageId = sc.nextInt();
            message.setMessageId(messageId);

            // Prompt for Message Date
            System.out.print("Enter Message Date (yyyy-mm-dd): ");
            String dateInput = sc.next();
            try {
                Date messageDate = Date.valueOf(dateInput);
                message.setMessageDate(messageDate);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                return;
            }

            // Prompt for Subscriber ID (assuming subscriber is already available in the DB)
            System.out.print("Enter Subscriber ID: ");
            int subscriberId = sc.nextInt();
            // Here, we assume that Subscriber is already present, you can add validation for Subscriber ID
            Subscriber subscriber = session.get(Subscriber.class, subscriberId);
            if (subscriber == null) {
                System.out.println("Subscriber with ID " + subscriberId + " does not exist.");
                return;
            }
            message.setSubscriber(subscriber);

            // Persist the Message object
            session.persist(message);
            tx.commit();
            System.out.println("Message added successfully.");
        }
    }

    // Method to update an existing Message
    @Override
    public void updateMessage(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();

            while (true) {
                System.out.println("Choose an option for update:\n1. Update Message Date\n2. Update Subscriber\n3. Exit");

                int option = sc.nextInt();
                Message message;
                switch (option) {
                    case 1:
                        // Prompt for Message ID to update
                        System.out.println("Enter Message ID to update:");
                        message = session.get(Message.class, sc.nextInt());
                        if (message != null) {
                            // Prompt for new Message Date
                            System.out.print("Enter new Message Date (yyyy-mm-dd): ");
                            String dateInput = sc.next();
                            try {
                                Date newMessageDate = Date.valueOf(dateInput);
                                message.setMessageDate(newMessageDate);
                                session.saveOrUpdate(message);
                                tx.commit();
                                System.out.println("Message Date updated successfully.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                            }
                        } else {
                            System.out.println("Message with the given ID not found.");
                        }
                        break;

                    case 2:
                        // Prompt for Message ID to update
                        System.out.println("Enter Message ID to update:");
                        message = session.get(Message.class, sc.nextInt());
                        if (message != null) {
                            // Prompt for new Subscriber ID
                            System.out.print("Enter new Subscriber ID: ");
                            int newSubscriberId = sc.nextInt();
                            Subscriber newSubscriber = session.get(Subscriber.class, newSubscriberId);
                            if (newSubscriber != null) {
                                message.setSubscriber(newSubscriber);
                                session.saveOrUpdate(message);
                                tx.commit();
                                System.out.println("Subscriber updated successfully.");
                            } else {
                                System.out.println("Subscriber with ID " + newSubscriberId + " not found.");
                            }
                        } else {
                            System.out.println("Message with the given ID not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting update Message.");
                        return;

                    default:
                        System.out.println("Choose a valid option.");
                        break;
                }
            }
        }
    }

    // Method to delete an existing Message
    @Override
    public void deleteMessage(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();

            System.out.println("Enter Message ID to delete:");
            int messageId = sc.nextInt();

            Message message = session.get(Message.class, messageId);
            if (message != null) {
                session.delete(message);
                tx.commit();
                System.out.println("Message deleted successfully.");
            } else {
                System.out.println("Message with ID " + messageId + " does not exist.");
            }
        }
    }

    // Method to retrieve a single Message by ID
    @Override
    public void getMessage(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            System.out.println("Enter Message ID to fetch:");
            int messageId = sc.nextInt();

            Message message = session.get(Message.class, messageId);

            if (message != null) {
                System.out.println("Message: " + message);
                System.out.println("Message details retrieved successfully.");
            } else {
                System.out.println("Message with ID " + messageId + " does not exist.");
            }
        }
    }

    // Method to retrieve all Messages
    @Override
    public void getAllMessage(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            // HQL query to fetch all Message records
            Query query = session.createQuery("from Message");
            List<Message> resultList = query.getResultList();

            for (Message message : resultList) {
                System.out.println(message);
            }
        }
    }
}
