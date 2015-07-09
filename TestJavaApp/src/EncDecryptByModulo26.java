
public class EncDecryptByModulo26 {

	public static void main(String[] args) {
		String msg="I am the handmaid of the Lord";
		String key="avemaria123";
		System.out.println("Input : "+msg);
		String encText=encrypt(msg, key);
		System.out.println("Encrypted : "+encText);
		System.out.println("Decrypted : "+decrypt(encText, key));
	}

	private static String encrypt(String input,String key){
		StringBuilder sb=new StringBuilder();
		int encryptInt;
		int n = key.hashCode() % 128;
		for (int i = 0; i < input.length(); i++) {
			int m=(int)(input.charAt(i) - ' ');
			//int n=(int)(key.charAt(i)- ' ');
			encryptInt= (m + n) % 128;
			sb.append((char)((encryptInt) +(int)' '));
		}
		return sb.toString();
	}
	
	private static String decrypt(String encText,String key){
		StringBuilder sb=new StringBuilder();
		int decryptInt;
		int n = key.hashCode() % 128;
		for (int i = 0; i < encText.length(); i++) {
			int m=(int)(encText.charAt(i) - ' ');
			//int n=(int)(key.charAt(i)- ' ');
			decryptInt= m - n;
			if(decryptInt < 0){ decryptInt += 128;}
			sb.append((char)((decryptInt) +(int)' '));
		}
		
		return sb.toString();
	}
}
