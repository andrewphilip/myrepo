import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class EncryptFileByPBEDES {

	public static void main(String[] args) throws Exception{
		if(args.length < 1){
			System.out.println("Usage: Enter the full path of the file to be encrypted.");
			System.exit(0);
		}
		System.out.println(args[0]);
		FileInputStream fin=new FileInputStream(args[0]);
		FileOutputStream fout=new FileOutputStream("c://tmp//test.des");
		String encPwd="faithhopecharity";
		PBEKeySpec kspec=new PBEKeySpec(encPwd.toCharArray());
		SecretKeyFactory kFact=SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
		SecretKey key= kFact.generateSecret(kspec);
		byte[] salt=new byte[8];
		Random rnd=new Random();
		rnd.nextBytes(salt);
		
		PBEParameterSpec paramSpec=new PBEParameterSpec(salt, 100);
		Cipher encCipher= Cipher.getInstance("PBEWithMD5AndTripleDES");
		encCipher.init(Cipher.ENCRYPT_MODE,key,paramSpec);
		fout.write(salt);
		
		byte[] input=new byte[1024];
		int cnt;
		while((cnt= fin.read(input)) != -1){
			byte[] result=encCipher.update(input, 0, cnt);
			if(result != null){
				fout.write(result);
			}
		}
		
		byte[] res= encCipher.doFinal();
		if(res != null){
			fout.write(res);
		}
		
		fin.close();
		fout.flush();
		fout.close();
	}

}
