package com.Telecommunication.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Telecommunication.entity.Subscriber;
import com.Telecommunication.service.SubscriberService;

public class SubscriberServiceImpl implements SubscriberService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertSubscriber(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Subscriber subscriber = new Subscriber();

        System.out.println("Welcome to Subscriber Management");

        System.out.print("Enter Subscriber Name: ");
        String name = sc.next();
        subscriber.setName(name);

        System.out.print("Enter Phone Number: ");
        long phoneNumber = sc.nextLong();
        subscriber.setPhoneNumber(phoneNumber);

        System.out.print("Enter Address: ");
        String address = sc.next();
        subscriber.setAddress(address);

        session.persist(subscriber); // Saves the Subscriber object to the database
        tx.commit();
        session.close();
        System.out.println("Subscriber added successfully.");
    }

    @Override
    public void updateSubscriber(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Subscriber subscriber;
        try {
            while (true) {
                System.out.println("Choose an option for update:\n1. Update Name\n2. Update Phone Number\n3. Update Address\n4. Exit");

                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter Subscriber ID to update:");
                        subscriber = session.get(Subscriber.class, sc.nextInt());
                        if (subscriber != null) {
                            System.out.print("Enter new Name: ");
                            subscriber.setName(sc.next());
                            session.saveOrUpdate(subscriber);
                            tx.commit();
                            System.out.println("Name updated successfully.");
                        } else {
                            System.out.println("Subscriber with given ID not found.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter Subscriber ID to update:");
                        subscriber = session.get(Subscriber.class, sc.nextInt());
                        if (subscriber != null) {
                            System.out.print("Enter new Phone Number: ");
                            subscriber.setPhoneNumber(sc.nextLong());
                            session.saveOrUpdate(subscriber);
                            tx.commit();
                            System.out.println("Phone Number updated successfully.");
                        } else {
                            System.out.println("Subscriber with given ID not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Enter Subscriber ID to update:");
                        subscriber = session.get(Subscriber.class, sc.nextInt());
                        if (subscriber != null) {
                            System.out.print("Enter new Address: ");
                            subscriber.setAddress(sc.next());
                            session.saveOrUpdate(subscriber);
                            tx.commit();
                            System.out.println("Address updated successfully.");
                        } else {
                            System.out.println("Subscriber with given ID not found.");
                        }
                        break;

                    case 4:
                        System.out.println("Exiting update Subscriber.");
                        tx.commit();
                        return;

                    default:
                        System.out.println("Choose a valid option.");
                        break;
                }
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSubscriber(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Enter Subscriber ID to delete:");
        int subscriberId = sc.nextInt();

        Subscriber subscriber = session.get(Subscriber.class, subscriberId);
        if (subscriber != null) {
            session.delete(subscriber);
            tx.commit();
            System.out.println("Subscriber deleted successfully.");
        } else {
            System.out.println("Subscriber with ID " + subscriberId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getSubscriber(SessionFactory sf) {
        session = sf.openSession();
        System.out.println("Enter Subscriber ID to fetch:");
        int subscriberId = sc.nextInt();

        Subscriber subscriber = session.get(Subscriber.class, subscriberId);

        if (subscriber != null) {
            System.out.println(subscriber);
            System.out.println("Subscriber details retrieved successfully.");
        } else {
            System.out.println("Subscriber with ID " + subscriberId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getAllSubscriber(SessionFactory sf) {
        session = sf.openSession();

        // HQL query to fetch all Subscriber records
        Query query = session.createQuery("from Subscriber");
        List<Subscriber> resultList = query.getResultList();

        for (Subscriber subscriber : resultList) {
            System.out.println(subscriber);
        }

        session.close();
    }
}
