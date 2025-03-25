package com.Telecommunication.service;

import org.hibernate.SessionFactory;

public interface InventoryService {

    void insertInventory(SessionFactory sf);
	
	void updateInventory(SessionFactory sf);
	
	void deleteInventory(SessionFactory sf);
	
	void getInventory(SessionFactory sf);
	
	void getAllInventory(SessionFactory sf);
}