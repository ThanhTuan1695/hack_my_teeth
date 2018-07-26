package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.security.MessageDigest;


import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService{
	
	//for pbkdf later
	public String getRandomString(int length) {
		
        try {
        		int leftLimit = 97; // letter 'a'
        		int rightLimit = 122; // letter 'z'
                Random random = new SecureRandom();
                StringBuilder buffer = new StringBuilder(length);
                for (int i = 0; i < length; i++) {
                    int randomLimitedInt = leftLimit + (int) 
                      (random.nextFloat() * (rightLimit - leftLimit + 1));
                    buffer.append((char) randomLimitedInt);
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
}
