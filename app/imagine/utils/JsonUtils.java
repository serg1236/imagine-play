package imagine.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static  ObjectMapper mapper = new ObjectMapper();
	
	public static JsonNode objectToJson(Object object){
		return mapper.valueToTree(object);
	}
	
	public static <T> T jsonToObject(JsonNode json, Class<T> type) throws JsonProcessingException{
		return mapper.treeToValue(json, type);
	}
	
	public static <T> T jsonToObject(String json, Class<T> type) throws IOException{
		return mapper.treeToValue(mapper.readTree(json), type);
	}
	
	
}
