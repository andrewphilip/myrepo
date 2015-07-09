import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class BlowFishKey {

	public static void main(String[] args) throws Exception{

		KeyGenerator kgen=KeyGenerator.getInstance("Blowfish");
		SecretKey skey=kgen.generateKey();
		byte[] raw=skey.getEncoded();
		SecretKeySpec kspec=new SecretKeySpec(raw, "Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, kspec);
		byte[] enc=cipher.doFinal("I am handmaid of the Lord.".getBytes());
		System.out.println(enc);

		Cipher cipher2=Cipher.getInstance("Blowfish");
		cipher2.init(Cipher.DECRYPT_MODE,kspec);
		byte[] decTxt=cipher2.doFinal(enc);
		System.out.println(new String(decTxt,"UTF8"));
	}

}
