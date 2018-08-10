package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.security.SecureRandom;


import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.security.MessageDigest;

import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.RandomNumberGenerator;
@Service
public class PasswordServiceImpl implements PasswordService{
	@Autowired
	private SessionService sessService;
	//for pbkdf later
	
    public RandomNumberGenerator random;

    public char[] symbols;

    public char[] buf;
    
	 public String nextString() {
	        for (int idx = 0; idx < buf.length; ++idx)
	            buf[idx] = symbols[random.next(16) % symbols.length];
	        return new String(buf);
	    }
	 
	public String getRandomString(int length) {
		
		try {
    		int leftLimit = 48; // letter '0'
    		int rightLimit = 122; // letter 'z'
            Random random = new SecureRandom();
            StringBuilder buffer = new StringBuilder(length);
       
            while(buffer.length() != length){
                int randomLimitedInt = leftLimit + (int) 
                  (random.nextFloat() * (rightLimit - leftLimit + 1));
                if((randomLimitedInt>=48 && randomLimitedInt <=57) || (randomLimitedInt>=97 && randomLimitedInt<=122)){
                    buffer.append((char) randomLimitedInt);
                } else{
                    continue;
                }
            }
        
            String generatedString = buffer.toString();
            return generatedString;
    } catch (Exception e) {
    		e.printStackTrace();
    }
        return null;
    }
	
	
	public String sha256(String password) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
	        byte[] result = mDigest.digest(password.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	        		String hex = Integer.toHexString(0xff & result[i]);
	            if(hex.length() == 1) sb.append('0');
	            	sb.append(hex);
	        } 
	        return sb.toString();
	    } catch (Exception e) {
	    		e.printStackTrace();
	    }
		return null;
	}

	public List<String> setPasswords() {
		
			List<String> result = new ArrayList<>();
			result.add("ardy123");
			result.add("doctor1");
			result.add("johnsonbabe");
			result.add("doctorvn");
			result.add("alex456");
			
			return result;
	    
	}
	
	public String pbkdf2 (String password, String salt) {
		int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return sessService.getHex(res);
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
	}
}
