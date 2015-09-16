package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@NamedQuery(query="Select user from User user Where user.fbLogin = :login", name="get user by login")
public class User {
	
	@GeneratedValue
	@Id
	private int id;
	private String fbLogin;
	
	@OneToMany(mappedBy="author")
	private List<Image> images;
	@Column(unique=true)
	private String name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFbLogin() {
		return fbLogin;
	}
	public void setFbLogin(String fbLogin) {
		this.fbLogin = fbLogin;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
