package andy.jaxb.unmarshall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;

public class JavaJSONApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ObjectMapper mapper=new ObjectMapper();
		Person person=new Person("andrew","philip",42,"fairfax","va","5716069215");
		
		try {
			mapper.writeValue(new File("c:\\xmldir\\person.json"), person);
			System.out.println(mapper.writeValueAsString(person));
			
			String jsonStr="{\"name\":\"andrew\", \"state\":\"virginia\"}";
			
			Map<String,String> aMap=new HashMap<String,String>();
			aMap=mapper.readValue(jsonStr, new TypeReference<Map<String,String>>() {});
			System.out.println(aMap);
			
			System.out.println(mapper.writeValueAsString(aMap));
			
			//JSON Tree Model
			
			BufferedReader in =new BufferedReader(new FileReader("c:\\xmldir\\person.json"));
			JsonNode rnode=mapper.readTree(in);
			System.out.println(rnode.path("firstName").getTextValue());
			System.out.println(rnode.path("lastName").getTextValue());
			System.out.println(rnode.path("age").getIntValue());
			
			
			((ObjectNode)rnode).put("firstname", "ANDREW");
			((ObjectNode)rnode).put("lastname", "PHILIP");
			((ObjectNode)rnode).put("city", "FAIRFAX");
			((ObjectNode)rnode).put("state", "VIRGINIA");
			
			mapper.writeValue(new File("c:\\xmldir\\person2.json"), rnode);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
