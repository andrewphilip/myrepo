import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class DecryptFileByPBEDES {

	public static void main(String[] args) throws Exception{
		String encPwd="faithhopecharity";
		PBEKeySpec kspec=new PBEKeySpec(encPwd.toCharArray());
		SecretKeyFactory kFact=SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
		SecretKey key= kFact.generateSecret(kspec);

		FileInputStream fin = new FileInputStream("c://tmp//test.des");
		byte[] salt=new byte[8];
		fin.read(salt);
		
		PBEParameterSpec paramSpec= new PBEParameterSpec(salt, 100);
		Cipher decCipher= Cipher.getInstance("PBEWithMD5AndTripleDES");
		decCipher.init(Cipher.DECRYPT_MODE,key,paramSpec);
		
		FileOutputStream fout=new FileOutputStream("c://tmp//test_decrypted.log");
		byte[] result=new byte[1028];
		int cnt;
		while((cnt= fin.read(result)) != -1){
			byte[] res=decCipher.update(result, 0, cnt);
			if(res != null){
				fout.write(res);
			}
		}
		
		
		byte[] res= decCipher.doFinal();
		if(res != null){
			fout.write(res);
		}
		
		fin.close();
		fout.flush();
		fout.close();
		
	}

}
