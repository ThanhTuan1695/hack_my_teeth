package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.util.List;

public interface PasswordService {
	public String getRandomString(int length);
	
	public String sha256(String password);
	
	public List<String> setPasswords();
}
