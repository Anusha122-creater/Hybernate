package com.Telecommunication.service;

import org.hibernate.SessionFactory;

public interface SubscriberService {
    
	void insertSubscriber(SessionFactory sf);
	
	void updateSubscriber(SessionFactory sf);
	
	void deleteSubscriber(SessionFactory sf);
	
	void getSubscriber(SessionFactory sf);
	
	void getAllSubscriber(SessionFactory sf);
}