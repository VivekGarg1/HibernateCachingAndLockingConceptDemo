package com.home.interceptor;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.home.entities.User;

public class LoggingInterceptor extends EmptyInterceptor {

   private static final long serialVersionUID = 1L;
  
   private static Logger logger = LogManager.getLogger(LoggingInterceptor.class);

   @Override
   public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
      logger.info("onSave method is called...");
      if (entity instanceof User) {
    	  User user = (User) entity;
         logger.info(user);
      }
      return super.onSave(entity, id, state, propertyNames, types);
   }
   @Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	   logger.info("onLoad method is called...");
	   if (entity instanceof User) {
	    	  User user = (User) entity;
	         logger.info(user);
	      }
		return super.onLoad(entity, id, state, propertyNames, types);
	}
}