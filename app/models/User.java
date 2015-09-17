package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="customer")
@NamedQuery(query="Select user from User user Where user.fbId = :login", name="get user by login")
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private int id;
	
	@Column(unique=true, name="fbLogin")
	@JsonProperty("id")
	private String fbId;
	
	@OneToMany(mappedBy="author")
	private List<Image> images;
	
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFbId() {
		return fbId;
	}
	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fbId=" + fbId + ", images=" + images
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
