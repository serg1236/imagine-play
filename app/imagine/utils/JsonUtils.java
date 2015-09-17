package imagine.utils;

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
	
	
}
