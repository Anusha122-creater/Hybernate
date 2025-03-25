package com.Telecommunication.service;

import org.hibernate.SessionFactory;

public interface MessageService {

    void insertMessage(SessionFactory sf);
	
	void updateMessage(SessionFactory sf);
	
	void deleteMessage(SessionFactory sf);
	
	void getMessage(SessionFactory sf);
	
	void getAllMessage(SessionFactory sf);

}
