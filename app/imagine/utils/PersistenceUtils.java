package imagine.utils;


import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Image;
import models.User;
import play.Logger;
import play.db.jpa.JPA;

public class PersistenceUtils {
	
	public static boolean isUserExists(String login){
		TypedQuery<User> query = JPA.em().createNamedQuery("get user by login", User.class);
		query.setParameter("login", login);
		return query.getResultList().size()>0;
	}
	
	public static void save(Object obj){
		JPA.em().persist(obj);
	}
	
	public static User getUser(String login){
		TypedQuery<User> query = JPA.em().createNamedQuery("get user by login", User.class);
		query.setParameter("login", login);
		return query.getSingleResult();
	}
	
	public static boolean isUserExists(User user){
		return isUserExists(user.getFbId());
	}
	
	public static void deleteImage(String url){
		Logger.info("before query");
		Query query = JPA.em().createNamedQuery("delete image");
		Logger.info("after query");
		query.setParameter("url", url);
		query.executeUpdate();
	}

}
