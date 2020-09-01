package com.home.client;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.home.entities.User;
import com.home.util.HibernateUtil;

public class FirstLevelOrSessionCacheClientTest {
	public static void main(String[] args) {
        //sessionCacheForInsertRecord();
        //sessionCacheReadByPrimaryKey();
		//sessionCacheReadByNaturalId();
		//sessionCacheForUpdateRecord();
		sessionCacheForDeleteRecord();
	}

	private static void sessionCacheForDeleteRecord() {
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			long userId=3;
			User user = session.get(User.class, userId);
			System.out.println(user);
			if(user!=null) {
				tx = session.beginTransaction();
				session.delete(user);
				tx.commit();
			}
			System.out.println("----------------------------");
			User user2 = session.get(User.class, userId);
			System.out.println(user2);
		}
		catch (Exception e) {
			if(tx!=null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

	private static void sessionCacheForUpdateRecord() {
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			String firstName="Aman";
			long userId=1;
			User user = session.get(User.class, userId);
			System.out.println(user);
			if(user!=null) {
				tx = session.beginTransaction();
				user.setFirstName(firstName);
				tx.commit();
			}
			System.out.println("----------------------------");
			User user2 = session.get(User.class, userId);
			System.out.println(user2);
		}
		catch (Exception e) {
			if(tx!=null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

	private static void sessionCacheReadByNaturalId() {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			String naturalId="Singh";
			User user1 = session.bySimpleNaturalId(User.class).load(naturalId);
			System.out.println(user1);
			System.out.println("----------------------------");
			User user2 = session.bySimpleNaturalId(User.class).load(naturalId);
			System.out.println(user2);
		}
		catch (Exception e) {
			throw e;
		}
	}

	private static void sessionCacheReadByPrimaryKey() {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			long userId=1;
			User user1 = session.get(User.class, userId);
			System.out.println(user1);
			System.out.println("----------------------------");
			User user2 = session.get(User.class, userId);
			System.out.println(user2);
		}
		catch (Exception e) {
			throw e;
		}
	}

	private static void sessionCacheForInsertRecord() {
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			User user = new User();
			user.setFirstName("Shubham");
			user.setLastName("Sharma");
			tx = session.beginTransaction();
			Long userId = (Long) session.save(user);
			tx.commit();
			System.out.println("----------------------------");
			User user2 = session.get(User.class, userId);
			System.out.println(user2);
		}
		catch (Exception e) {
			if(tx!=null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}
}
