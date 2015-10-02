package controllers;

import imagine.utils.CloudUtils;
import imagine.utils.JsonUtils;
import imagine.utils.PersistenceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import models.Image;
import models.User;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
    	Image image = PersistenceUtils.getImage("bl0nmw5qephgnkzkanxh");
    	JsonNode imageNode = JsonUtils.objectToJson(image);
    	Logger.info(imageNode.toString());
    	try{
    		Image im2 = JsonUtils.jsonToObject(imageNode, Image.class);
    		Logger.info(im2.getAuthor().getFirstName());
    	}catch(IOException e){
    		
    	}
       	
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
    	Map uploadResult = CloudUtils.uploadImage(image);
    	String userString = body.asFormUrlEncoded().get("user")[0];
    	User user = null;
		try {
			user = JsonUtils.jsonToObject(userString, User.class);
		} catch (IOException e) {
			Logger.error(e.getMessage());
		}
    	user = PersistenceUtils.getUser(user.getFbId());
    	user.addImage(uploadResult.get("url").toString(),uploadResult.get("public_id").toString());
    	PersistenceUtils.save(user);
    	userString = JsonUtils.objectToJson(user).toString();
    	Logger.info(userString);
    	return ok(userString);
    }
    
    public static Result deleteImage(){
    	JsonNode json = request().body().asJson();
    	Logger.info(json.toString());
    	String url = json.get("url").asText();
    	String publicId = json.get("public_id").asText();
    	Logger.info("DELETE: "+url);
    	try {
			CloudUtils.deleteImage(publicId);
			PersistenceUtils.deleteImage(url);
		} catch (IOException e) {
			Logger.error(e.getMessage());
		}
    	return ok("Ok");
    }
    
    public static Result getUsers(){
    	JsonNode json = request().body().asJson();
    	String userFbId = json.get("id").asText();
    	List<User> users = PersistenceUtils.getUserList(userFbId);
    	String usersString = JsonUtils.objectToJson(users).toString();
    	return ok(usersString);
    }
    
    public static Result getUser(){
    	User user = null;
    	JsonNode json = request().body().asJson();
    	Logger.info(json.toString());
    	String userId = json.get("id").asText();
    	user = PersistenceUtils.getUser(userId);
    	String userString = JsonUtils.objectToJson(user).toString();
    	return ok(userString);
    }
    
    public static Result getImage(){
    	Image image = null;
    	JsonNode json = request().body().asJson();
    	Logger.info(json.toString());
    	String imageId = json.get("id").asText();
    	image = PersistenceUtils.getImage(imageId);
    	String userString = JsonUtils.objectToJson(image).toString();
    	return ok(userString);
    }
    
    public static Result toggleLike(){
    	
    	return ok("Ok");
    }

}
