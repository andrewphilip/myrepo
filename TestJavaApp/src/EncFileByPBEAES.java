import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class EncFileByPBEAES {

	public static void main(String[] args) throws Exception {

		FileInputStream fin=new FileInputStream("c://tmp//test.log");
		FileOutputStream fout=new FileOutputStream("c://tmp//test_encrypted_aes.des");
		
		String pwd="adonai";
		byte[] salt=new byte[8];
		SecureRandom rnd=new SecureRandom();
		rnd.nextBytes(salt);
		
		FileOutputStream saltFile=new FileOutputStream("c://tmp//salt.enc");
		saltFile.write(salt);
		saltFile.close();
		
		SecretKeyFactory fact=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec=new PBEKeySpec(pwd.toCharArray(), salt, 65536,256);
		SecretKey key=fact.generateSecret(spec);
		SecretKey skey=new SecretKeySpec(key.getEncoded(), "AES");
		
		Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skey);
		AlgorithmParameters params= cipher.getParameters();
		FileOutputStream ivFile=new FileOutputStream("c://tmp//iv.enc");
		byte[] iv=params.getParameterSpec(IvParameterSpec.class).getIV();
		ivFile.write(iv);
		ivFile.close();
		
		byte[] input=new byte[256];
		int cnt;
		
		while((cnt=fin.read(input)) != -1){
			byte[] res=cipher.update(input,0,cnt);
			if(res != null){
				fout.write(res);
			}
		}
		
		byte[] res=cipher.doFinal();
		if(res != null){
			fout.write(res);
		}

		fin.close();
		fout.flush();
		fout.close();
		
		System.out.println("Encryption done.");
	}

}
