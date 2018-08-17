package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
@Service
public class CryptoServiceImpl implements CryptoService {
	@Autowired
	private SessionService sessService;
	@Override
	public String encodeHmacSHA(String key, String data){
		try {
			 Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			 SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
			 sha256_HMAC.init(secret_key);

			 return DatatypeConverter.printHexBinary((sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] deriveKey(String password, byte[] salt, int keyLen) {
		// TODO Auto-generated method stub
		try {
		    SecretKeyFactory kf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		    KeySpec specs = new PBEKeySpec(password.toCharArray(), salt, 1024, keyLen);
		    SecretKey key = kf.generateSecret(specs);
		    return key.getEncoded();
		}catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}

	@Override
	public String encryptAES(byte[] key, String plainText) {
		// TODO Auto-generated method stub
		try {
			byte[] clean = plainText.getBytes();
	
	        // Generating IV.
	        int ivSize = 16;
	        byte[] iv = new byte[ivSize];
	        SecureRandom random = new SecureRandom();
	        random.nextBytes(iv);
	        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
	        byte[] encrypted = cipher.doFinal(clean);
	        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
	        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
	        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

	        return DatatypeConverter.printHexBinary(encryptedIVAndText);
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public String decryptAES(byte[] key, String cipherText) {
		// TODO Auto-generated method stub
		try{
			byte[] encryptedIvTextBytes = DatatypeConverter.parseHexBinary(cipherText);
	        int ivSize = 16;

	        byte[] iv = new byte[ivSize];
	        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
	        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
	        int encryptedSize = encryptedIvTextBytes.length - ivSize;
	        byte[] encryptedBytes = new byte[encryptedSize];
	        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
	        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
	        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

	        return new String(decrypted);
		}catch(Exception e) {
			
		}
		return null;
	}

}
