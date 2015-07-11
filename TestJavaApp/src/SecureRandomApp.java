import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class SecureRandomApp {

	public static void main(String[] args) {

		try {
			SecureRandom secRandom=SecureRandom.getInstance("SHA1PRNG");
			secRandom.setSeed(711);
			byte[] b=new byte[20];
			secRandom.nextBytes(b);
			System.out.println(secRandom.nextInt());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
