package imagine.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import play.Logger;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

public class CloudUtils {
	
	private static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
			  "cloud_name", "imagine",
			  "api_key", "812128952259734",
			  "api_secret", "P1UZxaCJmpnPeAAFvl7bxOqqGHw"));
	
	
	//returns image url
	public static String uploadImage(File image, int width, int height){
		String url = null;
		try{
			Map uploadResult = cloudinary.uploader().upload(image,
					ObjectUtils.asMap("transformation",new Transformation().crop("pad").width(width).height(height).background("black")));
			url = (String) uploadResult.get("url");
			Logger.info(url);
		}catch(IOException e){
			Logger.error(e.getMessage());
		}
		return url;
	}
	
	//default sized upload
	public static String uploadImage(File image){
		return uploadImage(image, 500, 500);
	}
}
