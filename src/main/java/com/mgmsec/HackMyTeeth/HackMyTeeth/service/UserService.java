package com.mgmsec.HackMyTeeth.HackMyTeeth.service;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import java.util.List;
public interface UserService {
	List<User> findAll();	
	List<User> findByUsername(String username);	
	boolean validateUser(String username,String password);
	List<User> listDentist();
	
}
