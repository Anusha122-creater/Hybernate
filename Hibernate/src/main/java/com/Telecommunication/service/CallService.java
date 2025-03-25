package com.Telecommunication.service;

import org.hibernate.SessionFactory;

public interface CallService {

	void insertCall(SessionFactory sf);
	
	void updateCall(SessionFactory sf);
	
	void deleteCall(SessionFactory sf);
	
	void getCall(SessionFactory sf);
	
	void getAllCall(SessionFactory sf);
	
}