package andy.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AConcurrentMap {

	public static void main(String[] args) {
		String[] doctors={"Aquinas","Bonaventure", "Francis DeSales", "Augustine","Alphonse Liguori", 
				"Ambrose", "Anselm","John Chrysostom","Athanasius","Albert"};

		ConcurrentMap<String, String> aMap=new ConcurrentHashMap<String, String>();
		
		for(String s: doctors){
			aMap.put(s, s);
		}
		
		for(String s: aMap.keySet()){
			if(s.equals("Anselm")){
				aMap.put("Jerome","Jerome");
			}
		}
		
		System.out.println("Map size: "+aMap.size() );
		//displaying
		for(String s: aMap.keySet()){
			System.out.println(s);
		}	
		
	}

}
