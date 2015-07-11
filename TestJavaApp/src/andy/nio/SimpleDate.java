package andy.nio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		try {
			Date dt=sdf.parse("2014-10-03 08:30:00.0");
			String s=sdf2.format(dt);
			System.out.println(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
