package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.util.List;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
import com.mgmsec.HackMyTeeth.HackMyTeeth.dao.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecuritySettings securitySettings;
	
	@Autowired
	private PasswordService passwordService;
	
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
	
	public boolean resetAllPassword() {
		try {
			List<String> passwords = passwordService.setPasswords();
			List<String> hashedPasswords = new ArrayList<>();
			switch (securitySettings.getPwdStorage()) {
				case Clear:
					userRepository.resetAllPassword(passwords);
					
					break;
					
				case Hashed:
					for(String item: passwords) {
						hashedPasswords.add(passwordService.sha256(item));
					}
					userRepository.resetAllPassword(hashedPasswords);
					
					break;
					
				case SaltHashed: 
					userRepository.resetAllPassword(hashedPasswords);
					break;
				
				case PBKDF:
					userRepository.resetAllPassword(hashedPasswords);
					break;
				
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

		
}
