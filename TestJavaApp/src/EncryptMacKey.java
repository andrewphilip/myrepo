import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;


public class EncryptMacKey {

	public static void main(String[] args) throws Exception {

		KeyGenerator kgen=KeyGenerator.getInstance("HmacMD5");
		SecretKey skey=kgen.generateKey();
		
		Mac mac=Mac.getInstance("HmacMD5");
		mac.init(skey);
		byte[] output=mac.doFinal("I am the handmaid of the Lord.".getBytes());
		System.out.println(output);
		
		
	}

}
