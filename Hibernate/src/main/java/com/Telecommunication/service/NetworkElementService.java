package com.Telecommunication.service;

import org.hibernate.SessionFactory;

public interface NetworkElementService {
    
	void insertNetworkElement(SessionFactory sf);
	
	void updateNetworkElement(SessionFactory sf);
	
	void deleteNetworkElement(SessionFactory sf);
	
	void getNetworkElement(SessionFactory sf);
	
	void getAllNetworkElement(SessionFactory sf);

}
