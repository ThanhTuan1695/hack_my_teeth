package com.mgmsec.HackMyTeeth.HackMyTeeth.service;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;

import java.util.List;
public interface UserService {
	List<User> findAll();
	List<User> listDentist();
	String findByUsername(String username,String password);
	User getDentistById(String id, Boolean sqli);
	List<User> findByUser(String username,String password);
	boolean resetAllPassword();
	public List<User> searchDentist(String key);
	boolean changePassword(int id, String password,String username);
	
}
