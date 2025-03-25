package com.Telecommunication.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Telecommunication.entity.BillingCycle;
import com.Telecommunication.service.BillingCycleService;

public class BillingCycleServiceImpl implements BillingCycleService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertBillingCycle(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        BillingCycle billingCycle = new BillingCycle();

        System.out.println("Welcome to BillingCycle");

        System.out.println("Enter BillingCycle ID: ");
        int billingCycleId = sc.nextInt();
        billingCycle.setBillingCycleId(billingCycleId);

        // Handling Date Input
        try {
            System.out.print("Enter Billing Date (yyyy-MM-dd): ");
            String dateInput = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date billingDate = sdf.parse(dateInput);
            billingCycle.setBillingDate(billingDate);
        } catch (Exception e) {
            System.out.println("Invalid date format. Using current date.");
            billingCycle.setBillingDate(new Date());
        }

        System.out.print("Enter Billing Amount: ");
        double amount = sc.nextDouble();
        billingCycle.setAmount(amount);

        session.persist(billingCycle); // Saves the BillingCycle object to the database
        tx.commit();
        session.close();
    }

    @Override
    public void updateBillingCycle(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        BillingCycle billingCycle;
        try {
            while (true) {
                System.out.println("Choose an Option for Update:\n1. Update Billing Date\n2. Update Amount\n3. Exit");

                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter BillingCycle ID:");
                        billingCycle = session.get(BillingCycle.class, sc.nextInt());
                        if (billingCycle != null) {
                            System.out.println("Enter new Billing Date (yyyy-MM-dd):");
                            String newDateInput = sc.next();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date newBillingDate = sdf.parse(newDateInput);
                            billingCycle.setBillingDate(newBillingDate);
                            session.saveOrUpdate(billingCycle);
                            tx.commit();
                            System.out.println("Billing Date updated successfully.");
                        } else {
                            System.out.println("BillingCycle not found.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter BillingCycle ID:");
                        billingCycle = session.get(BillingCycle.class, sc.nextInt());
                        if (billingCycle != null) {
                            System.out.println("Enter new Amount:");
                            double newAmount = sc.nextDouble();
                            billingCycle.setAmount(newAmount);
                            session.saveOrUpdate(billingCycle);
                            tx.commit();
                            System.out.println("Amount updated successfully.");
                        } else {
                            System.out.println("BillingCycle not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting update BillingCycle.");
                        tx.commit();
                        return;

                    default:
                        System.out.println("Choose a valid option.");
                        break;
                }
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteBillingCycle(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Enter BillingCycle ID to delete:");
        int cycleId = sc.nextInt();

        BillingCycle billingCycle = session.get(BillingCycle.class, cycleId);
        if (billingCycle != null) {
            session.delete(billingCycle);
            tx.commit();
            System.out.println("BillingCycle deleted successfully.");
        } else {
            System.out.println("BillingCycle with ID " + cycleId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getAllBillingCycle(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        // HQL query to fetch all BillingCycle records
        Query query = session.createQuery("from BillingCycle order by billingCycleId");
        List<BillingCycle> resultList = query.getResultList();

        for (BillingCycle cycle : resultList) {
            System.out.println(cycle);
        }

        tx.commit();
        session.close();
    }

    @Override
    public void getBillingCycle(SessionFactory sf) {
        session = sf.openSession();
        System.out.println("Enter BillingCycle ID to fetch:");
        int cycleId = sc.nextInt();

        BillingCycle billingCycle = session.get(BillingCycle.class, cycleId);

        if (billingCycle != null) {
            System.out.println(billingCycle);
            System.out.println("BillingCycle details retrieved successfully.");
        } else {
            System.out.println("BillingCycle with ID " + cycleId + " does not exist.");
        }

        session.close();
    }
}
