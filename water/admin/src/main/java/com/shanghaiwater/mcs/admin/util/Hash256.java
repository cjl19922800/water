package com.shanghaiwater.mcs.admin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Hash256 {
	public static void main(String[] args) {
		String a = getSHA256Str("Sa@123456");
		System.out.println(a);
	}

	
	public static String getSHA256Str(String str) {
		MessageDigest messageDigest;
		String encdeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash;
			try {
				hash = messageDigest.digest(str.getBytes("UTF-8"));
				encdeStr = Hex.encodeHexString(hash);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return encdeStr;
		
	}
	
	
	

}
