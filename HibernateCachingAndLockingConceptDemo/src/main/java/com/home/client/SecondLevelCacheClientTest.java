package com.home.client;

import org.hibernate.Session;
import com.home.entities.User;
import com.home.util.HibernateUtil;

public class SecondLevelCacheClientTest {
	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			long userId=1;
			User user1 = session.get(User.class, userId);
			System.out.println(user1);
		}
		catch (Exception e) {
			throw e;
		}
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			long userId=1;
			User user1 = session.get(User.class, userId);
			System.out.println(user1);
		}
		catch (Exception e) {
			throw e;
		}
	}
}
