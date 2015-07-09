import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class EncrypAESApp {

	public static void main(String[] args) {
		byte[] msg="I am the handmaid of the Lord.".getBytes();
		try {
			KeyGenerator kgen=KeyGenerator.getInstance("AES");
			kgen.init(256); // key: 128,192,256
			SecretKey sKey=kgen.generateKey();
			Cipher cipDes=Cipher.getInstance("AES"); // 
			cipDes.init(Cipher.ENCRYPT_MODE, sKey);
			
			byte[] encryMsg=cipDes.doFinal(msg);
			System.out.println("Encrypted Message: "+encryMsg);

			Cipher cipDes2=Cipher.getInstance("AES");
			cipDes2.init(Cipher.DECRYPT_MODE, sKey);
			
			byte[] decrypMsg=cipDes2.doFinal(encryMsg);
			System.out.println("Decrypted Message: "+ new String(decrypMsg,"UTF8"));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
