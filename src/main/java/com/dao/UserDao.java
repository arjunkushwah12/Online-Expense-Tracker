package com.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.User;

public class UserDao 
{
	private SessionFactory factory=null;
	private Session session=null;
	private Transaction tx=null;
	
	
	public UserDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}


//	public boolean saveUser(User user)
//	{
//		boolean f=false ;
//		try 
//		{
//			session=factory.openSession();
//			session.beginTransaction();
//			session.save(user);
//			tx.commit();
//			f=true; 
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//			if(tx!=null)
//			{
//				f=false;
//				e.printStackTrace();
//			}
//		}
//		return f;
//		
//	}
	public boolean saveUser(User user) {
	    boolean f = false;
	    try {
	        session = factory.openSession();
	        session.beginTransaction(); // Start the transaction
	        session.save(user); // Save the user object
	        session.getTransaction().commit(); // Commit the transaction directly using the session
	        f = true;
	    } catch (Exception e) {
	        if (session.getTransaction() != null) {
	            session.getTransaction().rollback(); // Rollback the transaction in case of an exception
	        }
	        e.printStackTrace();
	    } finally {
	        session.close(); // Always close the session
	    }
	    return f;
	}
	
	public User login(String email,String password)
	{
		User u =null;
			session=factory.openSession();
			Query q=session.createQuery("from User where email=:em and password=:ps");
		
		q.setParameter("em", email);
		q.setParameter("ps", password);
		 u=(User) q.uniqueResult();
		
		
		return u; 
	}


}
