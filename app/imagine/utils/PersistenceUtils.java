package imagine.utils;


import java.util.List;

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
	
	public static List<User> getUserList(String excludedUser){
		List<User> users = null;
		TypedQuery<User> query = JPA.em().createNamedQuery("get user list", User.class);
		query.setParameter("excludedUser", excludedUser);
		users = query.getResultList();
		return users;
	}
	
	public static Image getImage(String publicId){
		Image image = null;
		TypedQuery<Image> query = JPA.em().createNamedQuery("get image", Image.class);
		query.setParameter("id", publicId);
		image = query.getSingleResult();
		return image;
	}

}
