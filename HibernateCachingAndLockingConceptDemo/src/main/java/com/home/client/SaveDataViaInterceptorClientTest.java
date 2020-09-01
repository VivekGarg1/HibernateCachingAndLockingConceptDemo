package com.home.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.home.entities.User;
import com.home.interceptor.LoggingInterceptor;
import com.home.util.HibernateUtil;

public class SaveDataViaInterceptorClientTest {
	
	private static final Logger logger = (Logger) LogManager.getLogger(SaveDataViaInterceptorClientTest.class);

	public static void main(String[] args) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().withOptions().interceptor(new LoggingInterceptor()).openSession()) {

			tx = session.beginTransaction();
			User user = new User();
			user.setFirstName("Vivek");
			user.setLastName("Garg");
			session.save(user);
			logger.info("User Record save successfully!!!");
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
		
		try (Session session = HibernateUtil.getSessionFactory().withOptions().interceptor(new LoggingInterceptor()).openSession()) {
			User user=session.get(User.class, 1l);
			logger.info(user);
		} catch (Exception e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}
	
}
