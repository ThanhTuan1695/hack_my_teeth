package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import java.util.ArrayList;
import java.util.List;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
import com.mgmsec.HackMyTeeth.HackMyTeeth.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


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
	public List<User> searchDentist(String key){
		return userRepository.searchDentist(key);
	}
	
	@Override
	public List<User> findByUser(String username, String password) {
		// TODO Auto-generated method stub
		switch (securitySettings.getPwdStorage()) {
		case PBKDF:
			String salt = userRepository.getSalt(username);
			if (salt == null)
				return null;
			else {
				password = passwordService.pbkdf2(password, salt);
			}
			break;
		default:
			break;
		}
		return userRepository.findByUser(username, password);
	}
	public User getDentistById(String id, Boolean sqli){
		return userRepository.getDentistById(id,sqli);
	}
	public boolean resetAllPassword() {
		try {
			List<String> passwords = passwordService.setPasswords();
			List<String> hashedPasswords = new ArrayList<>();
			System.out.println(securitySettings.getPwdStorage());
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
				
				case PBKDF:
					List<String> saltList = new ArrayList<>();
					for(String item: passwords) {
						String salt = passwordService.getRandomString(8);
						hashedPasswords.add(passwordService.pbkdf2(item, salt));
						saltList.add(salt);
					}
				 	userRepository.updateAllSaltColumn(saltList);
					userRepository.resetAllPassword(hashedPasswords);
					break;
					
				
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

		
}
