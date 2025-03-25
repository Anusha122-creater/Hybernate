package com.Telecommunication.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Telecommunication.entity.NetworkElement;
import com.Telecommunication.service.NetworkElementService;

public class NetworkElementServiceImpl implements NetworkElementService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertNetworkElement(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        NetworkElement networkElement = new NetworkElement();

        System.out.println("Welcome to Network Element Management");

        System.out.print("Enter Network Element Type: ");
        String type = sc.next();
        networkElement.setType(type);

        System.out.print("Enter Network Element Location: ");
        String location = sc.next();
        networkElement.setLocation(location);

        session.persist(networkElement); // Saves the NetworkElement object to the database
        tx.commit();
        session.close();
        System.out.println("Network Element added successfully.");
    }

    @Override
    public void updateNetworkElement(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        NetworkElement networkElement;
        try {
            while (true) {
                System.out.println("Choose an option for update:\n1. Update Type\n2. Update Location\n3. Exit");

                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter Network Element ID to update:");
                        networkElement = session.get(NetworkElement.class, sc.nextInt());
                        if (networkElement != null) {
                            System.out.print("Enter new Type: ");
                            networkElement.setType(sc.next());
                            session.saveOrUpdate(networkElement);
                            tx.commit();
                            System.out.println("Network Element Type updated successfully.");
                        } else {
                            System.out.println("Network Element with given ID not found.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter Network Element ID to update:");
                        networkElement = session.get(NetworkElement.class, sc.nextInt());
                        if (networkElement != null) {
                            System.out.print("Enter new Location: ");
                            networkElement.setLocation(sc.next());
                            session.saveOrUpdate(networkElement);
                            tx.commit();
                            System.out.println("Network Element Location updated successfully.");
                        } else {
                            System.out.println("Network Element with given ID not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting update Network Element.");
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
    public void deleteNetworkElement(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Enter Network Element ID to delete:");
        int networkElementId = sc.nextInt();

        NetworkElement networkElement = session.get(NetworkElement.class, networkElementId);
        if (networkElement != null) {
            session.delete(networkElement);
            tx.commit();
            System.out.println("Network Element deleted successfully.");
        } else {
            System.out.println("Network Element with ID " + networkElementId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getNetworkElement(SessionFactory sf) {
        session = sf.openSession();
        System.out.println("Enter Network Element ID to fetch:");
        int networkElementId = sc.nextInt();

        NetworkElement networkElement = session.get(NetworkElement.class, networkElementId);

        if (networkElement != null) {
            System.out.println(networkElement);
            System.out.println("Network Element details retrieved successfully.");
        } else {
            System.out.println("Network Element with ID " + networkElementId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getAllNetworkElement(SessionFactory sf) {
        session = sf.openSession();

        // HQL query to fetch all NetworkElement records
        Query query = session.createQuery("from NetworkElement");
        List<NetworkElement> resultList = query.getResultList();

        for (NetworkElement networkElement : resultList) {
            System.out.println(networkElement);
        }

        session.close();
    }
}
