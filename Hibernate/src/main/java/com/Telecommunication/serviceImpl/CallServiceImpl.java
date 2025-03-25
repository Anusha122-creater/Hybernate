package com.Telecommunication.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Telecommunication.entity.Call;
import com.Telecommunication.entity.Subscriber;
import com.Telecommunication.service.CallService;

public class CallServiceImpl implements CallService {

    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertCall(SessionFactory sf) {
        session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Call call = new Call();

        System.out.println("Enter values for Call (i.e callId, subscriberId, and callDate):");

        // Get Subscriber
        System.out.print("Enter Subscriber ID: ");
        int subscriberId = sc.nextInt();
        Subscriber subscriber = session.get(Subscriber.class, subscriberId);
        if (subscriber == null) {
            System.out.println("Subscriber not found.");
            return;
        }
        call.setSubscriber(subscriber);

        // Set Call Date
        System.out.print("Enter Call Date (yyyy-mm-dd): ");
        java.sql.Date callDate = java.sql.Date.valueOf(sc.next());
        call.setCallDate(callDate);

        session.persist(call); // Saves the Call object to the database
        transaction.commit();
        session.close();
    }

    @Override
    public void updateCall(SessionFactory sf) {
        session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Call call;
        try {
            while (true) {
                System.out.println("Choose option for update:\n1.Update Subscriber\n2.Update Call Date\n3.Exit");

                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter callId:");
                        call = session.get(Call.class, sc.nextInt());
                        System.out.println("Enter new Subscriber ID:");
                        int subscriberId = sc.nextInt();
                        Subscriber subscriber = session.get(Subscriber.class, subscriberId);
                        if (subscriber != null) {
                            call.setSubscriber(subscriber);
                            session.saveOrUpdate(call);
                            transaction.commit();
                            System.out.println("Updated Subscriber successfully.");
                        } else {
                            System.out.println("Subscriber not found.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter callId:");
                        call = session.get(Call.class, sc.nextInt());
                        System.out.println("Enter new Call Date (yyyy-mm-dd):");
                        java.sql.Date callDate = java.sql.Date.valueOf(sc.next());
                        call.setCallDate(callDate);
                        session.saveOrUpdate(call);
                        transaction.commit();
                        System.out.println("Updated Call Date successfully.");
                        break;

                    case 3:
                        System.out.println("Exiting from update Call.");
                        transaction.commit();
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
    public void deleteCall(SessionFactory sf) {
        session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Enter callId:");
        int callId = sc.nextInt();

        Call call = session.get(Call.class, callId);
        if (call != null) {
            session.delete(call);
            transaction.commit();
            System.out.println("Call deleted successfully.");
        } else {
            System.out.println("Call with id " + callId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getCall(SessionFactory sf) {
        session = sf.openSession();

        System.out.println("Enter callId:");
        int callId = sc.nextInt();

        Call call = session.get(Call.class, callId);

        if (call != null) {
            System.out.println(call);
            System.out.println("Call details retrieved successfully.");
        } else {
            System.out.println("Call with id " + callId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getAllCall(SessionFactory sf) {
        session = sf.openSession();

        // HQL query to fetch all Call records
        Query query = session.createQuery("from Call");
        List<Call> resultList = query.getResultList();

        for (Call call : resultList) {
            System.out.println(call);
        }

        session.close();
    }
}
