package com.home.client;

import java.util.List;

import org.hibernate.Session;

import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class QueryCacheClientTest {
	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			List<Phone> phoneList = session.createQuery("from Phone").setCacheable(true).setCacheRegion("phone.cache").list();
			for (Phone phone : phoneList) {
				System.out.println(phone);
			}
		}
		catch (Exception e) {
			throw e;
		}
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			List<Phone> phoneList = session.createQuery("from Phone").setCacheable(true).setCacheRegion("phone.cache").list();
			for (Phone phone : phoneList) {
				System.out.println(phone);
			}
		}
		catch (Exception e) {
			throw e;
		}
	}
}
