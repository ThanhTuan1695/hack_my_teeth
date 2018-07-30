package com.mgmsec.HackMyTeeth.HackMyTeeth.service;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import com.mgmsec.HackMyTeeth.HackMyTeeth.dao.SessionRepository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import java.util.List;
public interface SessionService {
	String getHex(byte[] raw);
	Cookie checkLoginCookie(HttpServletRequest request);
	String addSession(int userID,String username, int role,String cookieID = "");
	boolean delSession(String cookieID);
	List<Session> findByID(int userID);
	String random64char();
	Session findBySession(String cookString);
	boolean deleteAllSession();
}
