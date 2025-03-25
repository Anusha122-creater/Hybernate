package com.Telecommunication.main;

import java.util.Scanner;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Telecommunication.serviceImpl.BillingCycleServiceImpl;
import com.Telecommunication.serviceImpl.CallServiceImpl;
import com.Telecommunication.serviceImpl.InventoryServiceImpl;
import com.Telecommunication.serviceImpl.MessageServiceImpl;
import com.Telecommunication.serviceImpl.NetworkElementServiceImpl;
import com.Telecommunication.serviceImpl.PaymentServiceImpl;
import com.Telecommunication.serviceImpl.SubscriberServiceImpl;

public class TeleCommunication_Mgmt {
    public static void main(String[] args) {

    	    	Configuration cfg=new Configuration();
    	    	cfg.configure("hibernate.cfg.xml");
    	        SessionFactory factory = cfg.buildSessionFactory();
    	        Session session = factory.openSession();
    	  
    	        Scanner sc = new Scanner(System.in);

    	        boolean isRunning = true;
    	        try {
    	            while (isRunning) {
    	                System.out.println("---- Welcome to Telecommunication Application ----");
    	                System.out.println("Select an option:\n1. BillingCycle \n2. Call \n3. Inventory \n4. Message \n5.NetworkElement \n6. Payment \n7. Subscriber \n8. Exit");
    	                int choice = sc.nextInt();

    	                switch (choice) {
   	                    case 1:
   	                    	manageBillingCycle(sc, factory);
   	                        break;
    	                    case 2:
    	                        manageInventory(sc, factory);
    	                        break;
    	                    case 3:
   	                        manageMessage(sc, factory);
    	                        break;
    	                    case 4:
    	                        manageCall(sc, factory);
    	                        break;
    	                    case 5:
    	                        manageNetworkElement(sc, factory);
    	                        break;
    	                    case 6:
    	                        managePayment(sc, factory);
    	                        break;
    	                    case 7:
    	                        manageSubscriber(sc, factory);
    	                        break;
    	                    case 8:
    	                        isRunning = false;
    	                        System.out.println("Exiting the application.");
    	                        break;
    	                    default:
    	                        System.out.println("Invalid option. Please try again.");
    	                }
    	            }
    	        } finally {
    	            sc.close();
    	            session.close();
    	            factory.close();
    	        }
    	    }

    	    // Organizer Management
    	    private static void manageBillingCycle(Scanner sc, SessionFactory factory) {
    	    	BillingCycleServiceImpl billingService = new BillingCycleServiceImpl();
    	        while (true) {
    	            System.out.println("Organizer Management:\n1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
    	            int option = sc.nextInt();
    	            switch (option) {
    	                case 1:
    	                	billingService.insertBillingCycle(factory);
    	                    break;
    	                case 2:
    	                	billingService.updateBillingCycle(factory);
    	                    break;
    	                case 3:
    	                	billingService.deleteBillingCycle(factory);
    	                    break;
    	                case 4:
    	                	billingService.getAllBillingCycle(factory);
    	                    break;
    	                case 5:
    	                	billingService.getBillingCycle(factory);
    	                    break;
    	                case 6:
    	                	billingService.getBillingCycle(factory);
    	                    break;
    	                case 7:
    	                    return;
    	                default:
	                    System.out.println("Invalid choice. Try again.");
    	            }
    	        }
    	    }

    	    
    	    private static void manageCall(Scanner sc, SessionFactory factory) {
    	    	CallServiceImpl callService = new CallServiceImpl();
    	        while (true) {
    	            System.out.println("Organizer Management:\n1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
    	            int option = sc.nextInt();
    	            switch (option) {
    	                case 1:
    	                	callService.insertCall(factory);
    	                    break;
    	                case 2:
    	                	callService.updateCall(factory);
    	                    break;
    	                case 3:
    	                	callService.deleteCall(factory);
    	                    break;
    	                case 4:
    	                	callService.getAllCall(factory);
    	                    break;
    	                case 5:
    	                	callService.getCall(factory);
    	                    break;
    	                case 6:
    	                	callService.getCall(factory);
    	                    break;
    	                case 7:
    	                    return;
    	                default:
    	                    System.out.println("Invalid choice. Try again.");
    	            }
    	        }
    	    }

    	    
    	    
    	    private static void manageInventory(Scanner sc, SessionFactory factory) {
    	    	InventoryServiceImpl inventoryService = new InventoryServiceImpl();
    	        while (true) {
    	            System.out.println("Organizer Management:\n1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
    	            int option = sc.nextInt();
    	            switch (option) {
    	                case 1:
    	                	inventoryService.insertInventory(factory);
    	                    break;
    	                case 2:
    	                	inventoryService.updateInventory(factory);
    	                    break;
    	                case 3:
    	                	inventoryService.deleteInventory(factory);
    	                    break;
    	                case 4:
    	                	inventoryService.getAllInventory(factory);
    	                    break;
    	                case 5:
    	                	inventoryService.getInventory(factory);
    	                    break;
    	                case 6:
    	                	inventoryService.getInventory(factory);
    	                    break;
    	                case 7:
    	                    return;
    	                default:
    	                    System.out.println("Invalid choice. Try again.");
    	            }
    	        }
    	    }

    	    
    	    
    	    
    	    private static void manageMessage(Scanner sc, SessionFactory factory) {
    	    	MessageServiceImpl messageService = new MessageServiceImpl();
    	        while (true) {
    	            System.out.println("Organizer Management:\n1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
    	            int option = sc.nextInt();
    	            switch (option) {
    	                case 1:
    	                	messageService.insertMessage(factory);
    	                    break;
    	                case 2:
    	                	messageService.updateMessage(factory);
    	                    break;
    	                case 3:
    	                	messageService.deleteMessage(factory);
    	                    break;
    	                case 4:
    	                	messageService.getAllMessage(factory);
    	                    break;
    	                case 5:
    	                	messageService.getMessage(factory);
    	                    break;
    	                case 6:
    	                	messageService.getMessage(factory);
    	                    break;
    	                case 7:
    	                    return;
    	                default:
    	                    System.out.println("Invalid choice. Try again.");
    	            }
    	        }
    	    }

    	    
    	    
    	    private static void manageNetworkElement(Scanner sc, SessionFactory factory) {
    	    	NetworkElementServiceImpl networkElementService = new NetworkElementServiceImpl();
    	        while (true) {
    	            System.out.println("Organizer Management:\n1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
    	            int option = sc.nextInt();
    	            switch (option) {
    	                case 1:
    	                	networkElementService.insertNetworkElement(factory);
    	                    break;
    	                case 2:
    	                	networkElementService.updateNetworkElement(factory);
    	                    break;
    	                case 3:
    	                	networkElementService.deleteNetworkElement(factory);
    	                    break;
    	                case 4:
    	                	networkElementService.getAllNetworkElement(factory);
    	                    break;
    	                case 5:
    	                	networkElementService.getNetworkElement(factory);
    	                    break;
    	                case 6:
    	                	networkElementService.getNetworkElement(factory);
    	                    break;
    	                case 7:
    	                    return;
    	                default:
    	                    System.out.println("Invalid choice. Try again.");
    	            }
    	        }
    	    }

    	    

    	    private static void managePayment(Scanner sc, SessionFactory factory) {
    	    	PaymentServiceImpl paymentService = new PaymentServiceImpl();
    	        while (true) {
    	            System.out.println("Organizer Management:\n1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
    	            int option = sc.nextInt();
    	            switch (option) {
    	                case 1:
    	                	paymentService.insertPayment(factory);
    	                    break;
    	                case 2:
    	                	paymentService.updatePayment(factory);
    	                    break;
    	                case 3:
    	                	paymentService.deletePayment(factory);
    	                    break;
    	                case 4:
    	                	paymentService.getAllPayment(factory);
    	                    break;
    	                case 5:
    	                	paymentService.getPayment(factory);
    	                    break;
    	                case 6:
    	                	paymentService.getPayment(factory);
    	                    break;
    	                case 7:
    	                    return;
    	                default:
    	                    System.out.println("Invalid choice. Try again.");
    	            }
    	        }
    	    }

    	    

    	    private static void manageSubscriber(Scanner sc, SessionFactory factory) {
    	    	SubscriberServiceImpl subscriberService = new SubscriberServiceImpl();
    	        while (true) {
    	            System.out.println("Organizer Management:\n1. Insert\n2. Update\n3. Delete\n4. Get All\n5. Get Specific\n6. Count\n7. Exit");
    	            int option = sc.nextInt();
    	            switch (option) {
    	                case 1:
    	                	subscriberService.insertSubscriber(factory);
    	                    break;
    	                case 2:
    	                	subscriberService.updateSubscriber(factory);
    	                    break;
    	                case 3:
    	                	subscriberService.deleteSubscriber(factory);
    	                    break;
    	                case 4:
    	                	subscriberService.getAllSubscriber(factory);
    	                    break;
    	                case 5:
    	                	subscriberService.getSubscriber(factory);
    	                    break;
    	                case 6:
    	                	subscriberService.getSubscriber(factory);
    	                    break;
    	                case 7:
    	                    return;
    	                default:
    	                    System.out.println("Invalid choice. Try again.");
    	            }
    	        }
    	    }

    	}
