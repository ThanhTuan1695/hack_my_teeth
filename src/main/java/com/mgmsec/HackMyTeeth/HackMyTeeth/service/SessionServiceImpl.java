package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import com.mgmsec.HackMyTeeth.HackMyTeeth.dao.SessionRepository;
import java.util.Base64;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.List;

import java.text.SimpleDateFormat;  
import java.util.Date;  
@Service
public class SessionServiceImpl implements SessionService {
	private static final String    HEXES    = "0123456789ABCDEF";
	private static final int LENGTH = 32;
	private static SecureRandom RANDOM = new SecureRandom();
	@Autowired
	SecuritySettings secSettings;
	
	@Autowired
	SessionRepository sesRepo;
	public String getHex(byte[] raw) {
	    final StringBuilder hex = new StringBuilder(2 * raw.length);
	    for (final byte b : raw) {
	        hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
	    }
	    return hex.toString();
	}
	@Override
	public String random64char() {
		try {
			byte[]  resBuf = new byte[LENGTH];
			RANDOM.nextBytes(resBuf);
			return getHex(resBuf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Cookie checkLoginCookie(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		System.out.println("LIST OF COOKIES: " + cookies);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SESSIONCOOKIE"))
                    loginCookie = cookie;
                		break;
            }
        }
        System.out.println("THE FUCKING COOKIE IS:" + loginCookie);
		return loginCookie;
	}
	
	@Override
	public String addSession(int userID, String username, int role) {
		String cookieID = "";
		try {
		switch (secSettings.getUseCookie()) {
			case Base64:
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    Date date = new Date();  
			    String tobeEncode = Integer.toString(userID) + "|" + username + "|" + formatter.format(date);
				cookieID = java.util.Base64.getEncoder().encodeToString(tobeEncode.getBytes());
				
				break;
			case Secure:
				cookieID = random64char();
				
				break;
			}
		if( sesRepo.createNewCookie(userID, username, role, cookieID) == true) {
			return cookieID;
		}
		else {
			return null;
		}
		}catch(Exception e) {
			return null;
		}
		
	}
	@Override
	public boolean delSession(String cookieID) {
		// TODO Auto-generated method stub
		return sesRepo.deleteCookiebyString(cookieID);
	}
	@Override
	public List<Session> findByID(int userID) {
		
		return sesRepo.findByID(userID);
	}
	@Override
	public Session findBySession(String cookString) {
		try {
			List<Session> result= sesRepo.findBySession(cookString);
			if( result == null || result.size() == 0) {
				return null;
			}
			else {return result.get(0);}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean deleteAllSession() {
		if(sesRepo.deleteAllCookie())
			return true;
		return false;
	}
}
