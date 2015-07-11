import org.apache.commons.codec.binary.Base64;


public class Base64App {

	public static void main(String[] args) {

		String s="basic";
		
		byte[] bytes=Base64.encodeBase64(s.getBytes());
		System.out.println(new String(bytes)); //YmFzaWM=
	}

}
