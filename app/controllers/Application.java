package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
    			  "cloud_name", "dmqpxg0wn",
    			  "api_key", "812128952259734",
    			  "api_secret", "P1UZxaCJmpnPeAAFvl7bxOqqGHw"));
    	try{
	    	File toUpload = new File("Hello-World.png");
	    	Map uploadResult = cloudinary.uploader().upload(toUpload,
	    			ObjectUtils.asMap("public_id","sample1"));
	    	Logger.info((String) uploadResult.get("url"));
    	}catch(IOException e){
    		Logger.error(e.getMessage());
    	}
        return ok(index.render());
    }

}
