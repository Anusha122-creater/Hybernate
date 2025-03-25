package com.Telecommunication.service;

import org.hibernate.SessionFactory;

public interface BillingCycleService {

    void insertBillingCycle(SessionFactory sf);
    
	void updateBillingCycle(SessionFactory sf);
	
	void deleteBillingCycle(SessionFactory sf);
	
	void getBillingCycle(SessionFactory sf);
	
	void getAllBillingCycle(SessionFactory sf);
	
	

    
}