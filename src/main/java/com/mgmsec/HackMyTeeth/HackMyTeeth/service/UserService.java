package com.mgmsec.HackMyTeeth.HackMyTeeth.service;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import java.util.List;
public interface UserService {
	List<User> findAll();	
	String findByUsername(String username,String password);	
	
	
}
