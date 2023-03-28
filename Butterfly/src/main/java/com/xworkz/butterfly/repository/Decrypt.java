package com.xworkz.butterfly.repository;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Decrypt {
	
	public static void main(String[] args) {
		String value = decryptedPassword("c3VoYXMxNDM=");
	System.out.println(value);
	}
	
	public static String decryptedPassword(String encryptPassword) {
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(encryptPassword);
		String decrypt = new String(decode);
		System.out.println("DecryptedPassword" + decrypt);
		return decrypt;
	}
	
	public static String encryptPassword(String password) {
		Encoder encoder = Base64.getEncoder();
		String passwords = password;
		String encodeToString = encoder.encodeToString(passwords.getBytes());
		System.out.println("EncryptPassword " + encodeToString);
		return encodeToString;

	}

}
