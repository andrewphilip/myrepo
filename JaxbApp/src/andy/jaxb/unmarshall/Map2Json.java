package andy.jaxb.unmarshall;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Map2Json {

	public static void main(String[] args) {

		ObjectMapper obj = new ObjectMapper();
		
		Map<String,String> aMap=new HashMap<String,String>();
		
		//populating-> map
		aMap.put("10-01-2014", "Oct Series Data");
		aMap.put("09-01-2014", "Sep Series Data");
		aMap.put("08-01-2014", "Aug Series Data");
		
		try {
			String jsonStr=obj.writeValueAsString(aMap);
			System.out.println(jsonStr);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
