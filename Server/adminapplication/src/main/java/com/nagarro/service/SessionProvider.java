package com.nagarro.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.model.Customer;


/**
 * @author mayankgangwar
 * Singleton class which provide the object of DB Session
 */
public class SessionProvider {
	
	static Configuration conf;
	static SessionFactory sf;

	public static Session getSession() {
		if (sf == null) {
			conf = new Configuration().configure().addAnnotatedClass(Customer.class);
			sf = conf.buildSessionFactory();
		}
		return sf.openSession();
	}
}
