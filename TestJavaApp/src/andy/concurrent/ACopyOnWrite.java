package andy.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

public class ACopyOnWrite {

	public static void main(String[] args) {

		CopyOnWriteArrayList<String> ls=new CopyOnWriteArrayList<String>();
		String[] doctors={"Aquinas","Bonaventure", "Francis DeSales", "Augustine","Alphonse Liguori", 
							"Ambrose", "Anselm","John Chrysostom","Athanasius","Albert"};
		//populating
		for(String s: doctors){
			ls.add(s);
		}
		
		for(String s: ls){
			if(s.equals("Anselm")){
				ls.add("Jerome");
			}
		}
		
		System.out.println("List size: "+ls.size() +" index of Jerome is "+ ls.indexOf("Jerome"));
		//displaying
		for(String s: ls){
			System.out.println(s);
		}	
	}

}
