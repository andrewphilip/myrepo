package andy.bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class StringEncryption {

	private String text;

	public StringEncryption() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String encrypt2Base64(String s){
		System.out.println("In encrypt2Base64()");
		byte[] bytes=Base64.encodeBase64(s.getBytes());
		return new String(bytes);
	}
	
	public String encrypt2MD5(String s){

		System.out.println("In encrypt2MD5()");
		StringBuilder sb=new StringBuilder();
		String ret="";
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(s.getBytes());
			byte[] bytes=md.digest();
			BigInteger num=new BigInteger(1, bytes);
			ret=num.toString(16);
			for(byte b: bytes){
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//return sb.toString();
		return ret;
	}
	
	public String encrypt2SHA(String s){
		String ret="";
		System.out.println("In encrypt2SHA()");
		try {
			MessageDigest md=MessageDigest.getInstance("SHA");
			md.update(s.getBytes());
			byte[] bytes=md.digest();
			BigInteger num=new BigInteger(1, bytes);
			ret=num.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void checkThrowException(){
		System.out.println("In checkThrowException()");
		throw new IllegalArgumentException();
	}
	
	@Override
	public String toString() {
		return "StringEncryption [text=" + text + "]";
	}
	
	
}
