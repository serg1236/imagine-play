package controllers;

import imagine.utils.JsonUtils;
import imagine.utils.PersistenceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import models.User;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import play.*;
import play.mvc.*;
import views.html.*;
import play.db.*;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

@Transactional
public class Application extends Controller {

    public static Result index() {
//    	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//    			  "cloud_name", "dmqpxg0wn",
//    			  "api_key", "812128952259734",
//    			  "api_secret", "P1UZxaCJmpnPeAAFvl7bxOqqGHw"));
//    	try{
//	    	File toUpload = new File("Hello-World.png");
//	    	Map uploadResult = cloudinary.uploader().upload(toUpload,
//	    			ObjectUtils.asMap("public_id","sample1"));
//	    	Logger.info((String) uploadResult.get("url"));
//    	}catch(IOException e){
//    		Logger.error(e.getMessage());
//    	}
    	//User user = JPA.em().find(User.class,1);
        return ok(index.render());
    }
    
    public static Result auth(){
    	User user = null;
    	JsonNode json = request().body().asJson();
    	try {
			user = JsonUtils.jsonToObject(json, User.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	if(!PersistenceUtils.isUserExists(user)){
    		PersistenceUtils.saveUser(user);
    	}else{
    		user = PersistenceUtils.getUser(user.getFbId());
    	}
    	String userString = JsonUtils.objectToJson(user).toString();
    	Logger.info(userString);
    	return ok(userString);
    }

}
