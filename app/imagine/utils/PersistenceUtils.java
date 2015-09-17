package imagine.utils;


import javax.persistence.TypedQuery;

import models.User;
import play.db.jpa.JPA;

public class PersistenceUtils {
	
	public static boolean isUserExists(String login){
		TypedQuery<User> query = JPA.em().createNamedQuery("get user by login", User.class);
		query.setParameter("login", login);
		return query.getResultList().size()>0;
	}
	
	public static void saveUser(User user){
		JPA.em().persist(user);
	}
	
	public static User getUser(String login){
		TypedQuery<User> query = JPA.em().createNamedQuery("get user by login", User.class);
		query.setParameter("login", login);
		return query.getSingleResult();
	}
	
	public static boolean isUserExists(User user){
		return isUserExists(user.getFbId());
	}
	

}
