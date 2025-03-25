package com.Telecommunication.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Telecommunication.entity.Payment;
import com.Telecommunication.entity.BillingCycle; // Ensure BillingCycle is imported
import com.Telecommunication.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertPayment(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Payment payment = new Payment();

        System.out.println("Welcome to Payment Management");

        // Select BillingCycle from the database (assuming BillingCycle is another entity)
        System.out.print("Enter Billing Cycle ID: ");
        int billingCycleId = sc.nextInt();
        BillingCycle billingCycle = session.get(BillingCycle.class, billingCycleId);
        if (billingCycle != null) {
            payment.setBillingCycle(billingCycle); // Set BillingCycle entity
        } else {
            System.out.println("Billing Cycle not found. Please try again.");
            return;
        }

        System.out.print("Enter Payment Date (YYYY-MM-DD): ");
        Date paymentDate = new Date();
        payment.setPaymentDate(paymentDate);  // Converts java.util.Date to java.sql.Date

        System.out.print("Enter Payment Method: ");
        String paymentMethod = sc.next();
        payment.setPaymentMethod(paymentMethod);

        session.persist(payment); // Saves the Payment object to the database
        tx.commit();
        session.close();
        System.out.println("Payment added successfully.");
    }

    @Override
    public void updatePayment(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Payment payment;
        try {
            while (true) {
                System.out.println("Choose an option for update:\n1. Update Billing Cycle\n2. Update Payment Date\n3. Update Payment Method\n4. Exit");

                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter Payment ID to update:");
                        payment = session.get(Payment.class, sc.nextInt());
                        if (payment != null) {
                            System.out.print("Enter new Billing Cycle ID: ");
                            int billingCycleId = sc.nextInt();
                            BillingCycle billingCycle = session.get(BillingCycle.class, billingCycleId);
                            if (billingCycle != null) {
                                payment.setBillingCycle(billingCycle);
                                session.saveOrUpdate(payment);
                                tx.commit();
                                System.out.println("Billing Cycle updated successfully.");
                            } else {
                                System.out.println("Billing Cycle not found.");
                            }
                        } else {
                            System.out.println("Payment with given ID not found.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter Payment ID to update:");
                        payment = session.get(Payment.class, sc.nextInt());
                        if (payment != null) {
                            System.out.print("Enter new Payment Date (YYYY-MM-DD): ");
                            Date paymentDate = new Date();
                            payment.setPaymentDate(paymentDate);  // Converts java.util.Date to java.sql.Date
                            session.saveOrUpdate(payment);
                            tx.commit();
                            System.out.println("Payment Date updated successfully.");
                        } else {
                            System.out.println("Payment with given ID not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Enter Payment ID to update:");
                        payment = session.get(Payment.class, sc.nextInt());
                        if (payment != null) {
                            System.out.print("Enter new Payment Method: ");
                            payment.setPaymentMethod(sc.next());
                            session.saveOrUpdate(payment);
                            tx.commit();
                            System.out.println("Payment Method updated successfully.");
                        } else {
                            System.out.println("Payment with given ID not found.");
                        }
                        break;

                    case 4:
                        System.out.println("Exiting update Payment.");
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
    public void deletePayment(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Enter Payment ID to delete:");
        int paymentId = sc.nextInt();

        Payment payment = session.get(Payment.class, paymentId);
        if (payment != null) {
            session.delete(payment);
            tx.commit();
            System.out.println("Payment deleted successfully.");
        } else {
            System.out.println("Payment with ID " + paymentId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getPayment(SessionFactory sf) {
        session = sf.openSession();
        System.out.println("Enter Payment ID to fetch:");
        int paymentId = sc.nextInt();

        Payment payment = session.get(Payment.class, paymentId);

        if (payment != null) {
            System.out.println(payment);
            System.out.println("Payment details retrieved successfully.");
        } else {
            System.out.println("Payment with ID " + paymentId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getAllPayment(SessionFactory sf) {
        session = sf.openSession();

        // HQL query to fetch all Payment records
        Query query = session.createQuery("from Payment");
        List<Payment> resultList = query.getResultList();

        for (Payment payment : resultList) {
            System.out.println(payment);
        }

        session.close();
    }
}
