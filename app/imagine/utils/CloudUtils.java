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
	
	
	public static Map uploadImage(File image, int width, int height){
		Map uploadResult = null;
		try{
			uploadResult = cloudinary.uploader().upload(image,
					ObjectUtils.asMap("transformation",new Transformation().crop("pad").width(width).height(height).background("black")));
		}catch(IOException e){
			Logger.error(e.getMessage());
		}
		return uploadResult;
	}
	
	//default sized upload
	public static Map uploadImage(File image){
		return uploadImage(image, 500, 500);
	}
	
	public static void deleteImage(String publicId) throws IOException{
		cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true));
	}
}
