package andy.encryp;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptUsingSHA {

	public static void main(String[] args) {

		try {
			MessageDigest md= MessageDigest.getInstance("SHA-256"); // MD algorithms: MD5, SHA-1, SHA-256...
			String text="andy123";
			md.update(text.getBytes("UTF-8"));
			byte[] digest= md.digest();
			BigInteger num=new BigInteger(1, digest);
			System.out.println("Encrypted value is:"+ num.toString(16));
			System.out.println("Encrypted value is:"+ num.toString());
			
			PasswordEncoder enc=new BCryptPasswordEncoder();
			String hashedpwd=enc.encode("philip123");
			System.out.println(hashedpwd);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

}
