package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.util.List;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.dao.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	@Override
	public String findByUsername(String username,String password) {
		return userRepository.findByUsername(username,password);
	}
	@Override
	public List<User> listDentist (){
		return userRepository.listDentist();
	}

	@Override
	public List<User> findByUser(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUser(username, password);
	}
	
		
}
