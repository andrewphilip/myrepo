import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;



public class DecryptFileByPBEAES {

	public static void main(String[] args) throws Exception {

		//get the salt
		FileInputStream fSalt=new FileInputStream("c://tmp//salt.enc");
		byte[] salt=new byte[8];
		fSalt.read(salt);
		fSalt.close();
		
		//get the Iv
		FileInputStream fIV=new FileInputStream("c://tmp//iv.enc");
		byte[] iv=new byte[16];
		fIV.read(iv);
		fIV.close();
		
		String pwd="adonai";
		SecretKeyFactory fact=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec=new PBEKeySpec(pwd.toCharArray(), salt, 65536,256);
		SecretKey key=fact.generateSecret(spec);
		SecretKey sKey=new SecretKeySpec(key.getEncoded(), "AES");
		
		Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, sKey, new IvParameterSpec(iv));
		
		FileInputStream fin=new FileInputStream("c://tmp//test_encrypted_aes.des");
		FileOutputStream fout=new FileOutputStream("c://tmp//test_decrypt_aes.log");
		
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
		
		System.out.println("Done decrypted..");
	}

}
