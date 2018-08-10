package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.util.List;

public interface PasswordService {
	public String getRandomString(int length);
	
	public String sha256(String password);
	public String nextString();
	public List<String> setPasswords();
	public String pbkdf2 (String password, String salt);
}
