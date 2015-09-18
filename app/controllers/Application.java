package controllers;

import imagine.utils.CloudUtils;
import imagine.utils.JsonUtils;
import imagine.utils.PersistenceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import models.User;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import play.*;
import play.mvc.*;
import play.mvc.BodyParser.Json;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import views.html.*;
import views.html.defaultpages.error;
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
    		PersistenceUtils.save(user);
    	}else{
    		user = PersistenceUtils.getUser(user.getFbId());
    	}
    	String userString = JsonUtils.objectToJson(user).toString();
    	Logger.info(userString);
    	return ok(userString);
    }
    
    public static Result uploadImage(){
    	
    	MultipartFormData body = request().body().asMultipartFormData();
    	FilePart imagePart = body.getFile("file");   	
    	File image = imagePart.getFile();
    	String url = CloudUtils.uploadImage(image);
    	String userString = body.asFormUrlEncoded().get("user")[0];
    	User user = null;
		try {
			user = JsonUtils.jsonToObject(userString, User.class);
		} catch (IOException e) {
			Logger.error(e.getMessage());
		}
    	user = PersistenceUtils.getUser(user.getFbId());
    	user.addImage(url);
    	PersistenceUtils.save(user);
    	userString = JsonUtils.objectToJson(user).toString();
    	Logger.info(userString);
    	return ok(userString);
    }
    
    public static Result deleteImage(){
    	JsonNode json = request().body().asJson();
    	String url = json.get("url").asText();
    	Logger.info("DELETE: "+url);
    	PersistenceUtils.deleteImage(url);
    	return ok("Ok");
    }

}
