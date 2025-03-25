package com.Telecommunication.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Telecommunication.entity.Inventory;
import com.Telecommunication.service.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    Scanner sc = new Scanner(System.in);
    Session session;

    @Override
    public void insertInventory(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Inventory inventory = new Inventory();

        System.out.println("Welcome to Inventory Management");

        System.out.println("Enter Inventory ID: ");
        int inventoryId = sc.nextInt();
        inventory.setInventoryId(inventoryId);

        System.out.print("Enter Equipment Type: ");
        String equipmentType = sc.next();
        inventory.setEquipmentType(equipmentType);

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        inventory.setQuantity(quantity);

        session.persist(inventory); // Saves the Inventory object to the database
        tx.commit();
        session.close();
        System.out.println("Inventory added successfully.");
    }

    @Override
    public void updateInventory(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Inventory inventory;
        try {
            while (true) {
                System.out.println("Choose an option for update:\n1. Update Equipment Type\n2. Update Quantity\n3. Exit");

                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter Inventory ID to update:");
                        inventory = session.get(Inventory.class, sc.nextInt());
                        if (inventory != null) {
                            System.out.print("Enter new Equipment Type: ");
                            inventory.setEquipmentType(sc.next());
                            session.saveOrUpdate(inventory);
                            tx.commit();
                            System.out.println("Equipment Type updated successfully.");
                        } else {
                            System.out.println("Inventory with given ID not found.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter Inventory ID to update:");
                        inventory = session.get(Inventory.class, sc.nextInt());
                        if (inventory != null) {
                            System.out.print("Enter new Quantity: ");
                            inventory.setQuantity(sc.nextInt());
                            session.saveOrUpdate(inventory);
                            tx.commit();
                            System.out.println("Quantity updated successfully.");
                        } else {
                            System.out.println("Inventory with given ID not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting update Inventory.");
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
    public void deleteInventory(SessionFactory sf) {
        session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Enter Inventory ID to delete:");
        int inventoryId = sc.nextInt();

        Inventory inventory = session.get(Inventory.class, inventoryId);
        if (inventory != null) {
            session.delete(inventory);
            tx.commit();
            System.out.println("Inventory deleted successfully.");
        } else {
            System.out.println("Inventory with ID " + inventoryId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getInventory(SessionFactory sf) {
        session = sf.openSession();
        System.out.println("Enter Inventory ID to fetch:");
        int inventoryId = sc.nextInt();

        Inventory inventory = session.get(Inventory.class, inventoryId);

        if (inventory != null) {
            System.out.println(inventory);
            System.out.println("Inventory details retrieved successfully.");
        } else {
            System.out.println("Inventory with ID " + inventoryId + " does not exist.");
        }

        session.close();
    }

    @Override
    public void getAllInventory(SessionFactory sf) {
        session = sf.openSession();

        // HQL query to fetch all Inventory records
        Query query = session.createQuery("from Inventory");
        List<Inventory> resultList = query.getResultList();

        for (Inventory inventory : resultList) {
            System.out.println(inventory);
        }

        session.close();
    }
}
